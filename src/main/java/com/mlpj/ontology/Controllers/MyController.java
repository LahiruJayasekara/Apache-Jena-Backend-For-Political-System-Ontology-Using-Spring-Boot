package com.mlpj.ontology.Controllers;

import com.mlpj.ontology.JenaWork.InitJena;
import net.minidev.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    @CrossOrigin
    @RequestMapping(value = "/findPolitician",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<JSONObject> findPolitician(@RequestBody RequestPOJO requestPOJO) {
        boolean allEmpty = true;
        System.out.println(requestPOJO);
        String queryString =
                "PREFIX pol: <http://www.semanticweb.org/viva/ontologies/2019/6/politicians#>" +
                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                        "SELECT ?Y WHERE { ";

        if (requestPOJO.getDistrict() != null && !requestPOJO.getDistrict().equals("")) {
            allEmpty = false;
            queryString += "?Y pol:isElectedFrom pol:" + requestPOJO.getDistrict() + ".";
        }

        if (requestPOJO.getProvince() != null && !requestPOJO.getProvince().equals("")) {
            allEmpty = false;
            queryString += "?Z pol:isDistrictOf pol:" + requestPOJO.getProvince() + "."
                            + "?Y pol:isElectedFrom ?Z .";
        }

        if (requestPOJO.getParty() != null && !requestPOJO.getParty().equals("")) {
            allEmpty = false;
            queryString += "?Y pol:isMemberOfParty pol:" + requestPOJO.getParty() + ".";
        }

        if (requestPOJO.getPoliticalInstituteInstance() != null && !requestPOJO.getPoliticalInstituteInstance().equals("")) {
            allEmpty = false;
            queryString += "?A rdfs:subPropertyOf pol:isMemberOfInstitution. ?Y ?A pol:" + requestPOJO.getPoliticalInstituteInstance() + ".";
        }

        if (allEmpty) {
            List<JSONObject> emptyArray = new ArrayList<>();
            return emptyArray;
        }
        queryString += " }";
        System.out.println(queryString);

        List<JSONObject> resultSet = InitJena.findPoliticians(queryString);
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/describePolitician")
    public List<JSONObject> describePolitician(@RequestParam String politician_name) {

        String queryString =
                "PREFIX pol: <http://www.semanticweb.org/viva/ontologies/2019/6/politicians#>" +
                        "SELECT ?Y ?Z WHERE { pol:" + politician_name + " ?Y ?Z .}";
        List<JSONObject> resultSet = InitJena.describePoliticians(queryString);
        System.out.println(queryString);
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getProvinces")
    public List<JSONObject> getProvinces() {

        String queryString =
                "PREFIX pol: <http://www.semanticweb.org/viva/ontologies/2019/6/politicians#>" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "SELECT ?X WHERE { ?X rdf:type pol:Province .}";
        List<JSONObject> resultSet = InitJena.getItems(queryString);
        System.out.println(queryString);
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getDistricts")
    public List<JSONObject> getDistricts() {

        String queryString =
                "PREFIX pol: <http://www.semanticweb.org/viva/ontologies/2019/6/politicians#>" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "SELECT ?X WHERE { ?X rdf:type pol:District .}";
        List<JSONObject> resultSet = InitJena.getItems(queryString);
        System.out.println(queryString);
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getParties")
    public List<JSONObject> getParties() {

        String queryString =
                "PREFIX pol: <http://www.semanticweb.org/viva/ontologies/2019/6/politicians#>" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>" +
                        "SELECT ?X WHERE { ?Y rdfs:subClassOf pol:Party ." +
                                            "?X rdf:type ?Y}";
        List<JSONObject> resultSet = InitJena.getItems(queryString);
        System.out.println(queryString);
        return resultSet;
    }

    @CrossOrigin
    @RequestMapping("/getPoliticalInstituteInstances")
    public List<JSONObject> getPoliticalInstituteInstances() {

        String queryString =
                "PREFIX pol: <http://www.semanticweb.org/viva/ontologies/2019/6/politicians#>" +
                        "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                        "PREFIX rdfs:  <http://www.w3.org/2000/01/rdf-schema#>" +
                        "SELECT ?X WHERE { ?Y rdfs:subClassOf* pol:PoliticalInstitution ." +
                        "?X rdf:type ?Y}";
        List<JSONObject> resultSet = InitJena.getItems(queryString);
        System.out.println(queryString);
        return resultSet;
    }
}
