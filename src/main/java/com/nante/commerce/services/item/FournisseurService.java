package com.nante.commerce.services.item;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nante.commerce.crud.service.GenericService;
import com.nante.commerce.model.item.Article;
import com.nante.commerce.model.item.Fournisseur;
import com.nante.commerce.model.proforma.DemandeProforma;
import com.nante.commerce.model.proforma.DemandeProformaDetails;
import com.nante.commerce.model.proforma.DemandeProformaFournisseur;
import com.nante.commerce.model.proforma.ProformaBesoin;
import com.nante.commerce.repositories.demande.DemandeRepository;
import com.nante.commerce.repositories.item.ArticleRepository;
import com.nante.commerce.repositories.item.FournisseurRepository;
import com.nante.commerce.repositories.proforma.DemandeProformaDetailsRepo;
import com.nante.commerce.repositories.proforma.DemandeProformaFournisseurRepo;
import com.nante.commerce.repositories.proforma.DemandeProformaRepo;
import com.nante.commerce.repositories.proforma.ProformaBesoinRepo;
import com.nante.commerce.services.demande.DemandeService;
import com.nante.commerce.types.MailParam;
import com.nante.commerce.types.response.Response;

import jakarta.transaction.Transactional;

@Service
public class FournisseurService extends GenericService<Fournisseur> {
    @Autowired
    FournisseurRepository fournisseurRepo;

    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private DemandeService demandeService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private DemandeProformaRepo demandeProformaRepo;

    @Autowired
    private DemandeProformaDetailsRepo demandeProformaDetailsRepo;

    @Autowired
    DemandeProformaFournisseurRepo demandeProformaFournisseurRepo;

    @Autowired
    ProformaBesoinRepo proformaBesoinRepo;

    public List<Fournisseur> findForArticles(List<Integer> articles) {
        return fournisseurRepo.findForArticles(articles);
    }

    @Transactional
    public void makeDemandeProforma(String delaiLivraison, List<Integer> fournisseursIds,
            List<Integer> demandesIds) {
        List<Object[]> articlesQtes = this.demandeRepository.findArticlesWithQteOf(demandesIds);
        List<Fournisseur> fournisseurs = this.fournisseurRepo.findAllById(fournisseursIds);
        // HashMap<String, Object> articles = new HashMap<String, Object>();
        List<Article> articles = new ArrayList<>();

        articlesQtes.stream().forEach((articleQte) -> {
            Article a = articleRepository.findById(Integer.parseInt(articleQte[1].toString())).get();
            a.setQte(Double.parseDouble(articleQte[0].toString()));
            articles.add(a);
        });

        // Parametres pour le mail
        MailParam mailParam = new MailParam();
        mailParam.setDelaiLivraison(delaiLivraison);
        mailParam.setFournisseurs(fournisseurs);
        mailParam.setArticles(articles);

        // Save demande proforma
        DemandeProforma demandeProforma = new DemandeProforma();
        demandeProforma.setDelaiLivraison(delaiLivraison);
        demandeProforma.setJourDemande(LocalDate.now());
        demandeProforma.setReference(this.demandeService.generateReferenceDemandeProforma());

        demandeProforma = this.demandeProformaRepo.save(demandeProforma);

        for (Article a : articles) {
            DemandeProformaDetails demandeProformaDetails = new DemandeProformaDetails();
            demandeProformaDetails.setIdDemandeProforma(demandeProforma.getId());
            demandeProformaDetails.setIdArticle(a.getId());
            demandeProformaDetails.setQuantite(a.getQte());
            demandeProformaDetails = this.demandeProformaDetailsRepo.save(demandeProformaDetails);

            demandeProformaDetailsRepo.save(demandeProformaDetails);
        }

        for (Fournisseur fournisseur : fournisseurs) {
            DemandeProformaFournisseur demandeProformaFournisseur = new DemandeProformaFournisseur();
            demandeProformaFournisseur.setFournisseur(fournisseur);
            demandeProformaFournisseur.setIdDemandeProforma(demandeProforma.getId());
            demandeProformaFournisseur.setReference(
                    this.demandeService.generateReferenceDemandeProformaFournisseur(demandeProforma, fournisseur));

            demandeProformaFournisseurRepo.save(demandeProformaFournisseur);
        }

        for (int idDemande : demandesIds) {
            ProformaBesoin proformaBesoin = new ProformaBesoin();
            proformaBesoin.setIdDemande(idDemande);
            proformaBesoin.setIdProforma(demandeProforma.getId());
            proformaBesoinRepo.save(proformaBesoin);
        }

        // this.sendMail(mailParam);
    }

    private ResponseEntity<?> sendMail(MailParam param) {
        try {
            // send mail
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<MailParam> requestEntity = new HttpEntity<MailParam>(param, headers);

            String url = "http://localhost:4444/mailer";
            ResponseEntity<?> response = ResponseEntity.ok()
                    .body(new Response(restTemplate.postForObject(url, requestEntity, Object.class), ""));

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
