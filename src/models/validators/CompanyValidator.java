package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Company;
import utils.DBUtil;;

public class CompanyValidator {
    public static List<String> validate(Company c,Boolean companiesDuplicateCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String name_error = _validateName(c.getName());
        if(!name_error.equals("")) {
            errors.add(name_error);
        }

        String client_error = _validateClient(c.getClient());
        if(!client_error.equals("")){
        	errors.add(client_error);
        }

        String tell_error = _validateTell(c.getTell());
        if(!tell_error.equals("")) {
            errors.add(tell_error);
        }

        String address_error = _validateAddress(c.getAddress());
        if(!address_error.equals("")) {
        	errors.add(address_error);

        }
            String companies_error = _validateCompanies(c, companiesDuplicateCheckFlag);
            if(!companies_error.equals("")) {
            	errors.add(companies_error);
        }
        return errors;
    }

    private static String _validateName(String name) {
        if( name== null || name.equals("")) {
            return "会社名を入力してください。";
            }

        return "";
    }

    private static String _validateClient(String client){
    	if( client==null || client.equals("")){
    		return "担当者名を入力してください。";
    	}

    	return"";

    }

    private static String _validateTell(String tell) {
        if(tell == null || tell.equals("")) {
            return "電話番号を入力してください。";
            }

        return "";
    }
    private static String _validateAddress(String address) {
        if(address == null || address.equals("")) {
            return "住所を入力してください。";
            }

        return "";
    }
    private static String _validateCompanies(Company c,Boolean companiesDuplicateCheckFlag) {
    if(companiesDuplicateCheckFlag) {
        EntityManager em = DBUtil.createEntityManager();
        long companies_count = (long)em.createNamedQuery("checkRegisteredCompanies", Long.class)
        		.setParameter("name", c.getName())
        		.setParameter("client",c.getClient())
        		.setParameter("tell",c.getTell())
        		.setParameter("address",c.getAddress())
        		.getSingleResult();
        em.close();
        if(companies_count > 0) {
            return "入力された会社は既に存在しています。";
        }
    }

    return "";
}
}