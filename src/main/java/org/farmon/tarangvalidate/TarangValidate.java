/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.farmon.tarangvalidate;
import java.util.Date;
import java.util.List;
import org.bhaduri.tarangdto.ScripsDTO;
import org.bhaduri.tarangcall.scrips.Scrips;
import org.bhaduri.tarangdbservice.entities.Calltable;
import org.bhaduri.tarangdbservice.entities.Minutedata;
import org.bhaduri.tarangdbservice.entities.Validatecall;
import org.bhaduri.tarangdbservice.services.MasterDataServices;

/**
 *
 * @author sb
 */
public class TarangValidate {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        List<ScripsDTO> scriplist = new Scrips().getScripList();
        double price;
        String callone;
        String calltwo;
        
        String scrip;
        
        Date startdate;
        Date enddate;
        for (int i = 0; i < scriplist.size(); i++) {
            MasterDataServices mds = new MasterDataServices();
            List<Calltable> callListTwoMonths = mds.getCaldataForTwoMonths(scriplist.get(i).getScripId());
            scrip= scriplist.get(i).getScripId();
            for (int i1 = 0; i1 < callListTwoMonths.size()-1; i1++){
                Validatecall record = new Validatecall();
                price = callListTwoMonths.get(i1+1).getPrice();
                callone = callListTwoMonths.get(i1+1).getCallone();
                calltwo = callListTwoMonths.get(i1+1).getCalltwo();
                startdate = callListTwoMonths.get(i1+1).getCalltablePK().getLastupdateminute();                
                enddate = callListTwoMonths.get(i1).getCalltablePK().getLastupdateminute();
                
                if((callone.equals("buy") && calltwo.equals("buy"))||
                   (callone.equals("buy") && calltwo.equals("no"))||
                   (callone.equals("no") && calltwo.equals("buy"))){
                   List<Minutedata> validbuydata = mds.getValidBuyCallData(scrip, 
                           startdate, enddate, price);
                   if(callone.equals("buy")){
                       
                   }
                   
                }
                
                if((callone.equals("sell") && calltwo.equals("sell"))||
                   (callone.equals("sell") && calltwo.equals("no"))||
                   (callone.equals("no") && calltwo.equals("sell"))){
                   List<Minutedata> validselldata = mds.getValidSellCallData(scrip, 
                           startdate, enddate, price);
                }
                
                if((callone.equals("buy") && calltwo.equals("sell"))||
                        (callone.equals("sell") && calltwo.equals("buy"))){
                   List<Minutedata> validbuydata = mds.getValidBuyCallData(scrip, 
                           startdate, enddate, price);
                   List<Minutedata> validselldata = mds.getValidSellCallData(scrip, 
                           startdate, enddate, price);
                }
            }
        }
    }
}
