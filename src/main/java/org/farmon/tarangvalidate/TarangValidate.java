/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.farmon.tarangvalidate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.bhaduri.tarangdto.ScripsDTO;
import org.bhaduri.tarangcall.scrips.Scrips;
import org.bhaduri.tarangdbservice.entities.Calltable;
import org.bhaduri.tarangdbservice.entities.Validatecall;
import org.bhaduri.tarangdbservice.services.MasterDataServices;
import org.bhaduri.tarangdto.ValidateCallRec;
import org.farmon.tarangvalidate.FillValidateCall;

/**
 *
 * @author sb
 */
public class TarangValidate {

    public static void main(String[] args) throws ParseException {
        System.out.println("Hello World!");
        new FillValidateCall(61).updateValidatecall();
//        new FillValidateCall(2).updateValidatecall();
        
//        List<ScripsDTO> scriplist = new Scrips().getScripList();
//        double price;
//        double calculatedMargin = 0;
//        String callone;
//        String calltwo;        
//        String scrip;  
//        Date tempstartdate;
//        String modifiedString;
//        Date startdate;
//        String dateString;
//        
//        Date enddate;
//        MasterDataServices mds = new MasterDataServices();
//        Validatecall oldestrec = new Validatecall();
//        for (int i = 0; i < scriplist.size(); i++) {            
//            List<Calltable> callListTwoMonths = mds.getCaldataForTwoMonths(scriplist.get(i).getScripId());
//            scrip= scriplist.get(i).getScripId();
//            for (int i1 = 0; i1 < callListTwoMonths.size()-1; i1++){
//                
//                ValidateCallRec record = new ValidateCallRec();
//                price = callListTwoMonths.get(i1+1).getPrice();
//                callone = callListTwoMonths.get(i1+1).getCallone();
//                calltwo = callListTwoMonths.get(i1+1).getCalltwo();
//                
//                tempstartdate = callListTwoMonths.get(i1).getCalltablePK().getLastupdateminute();                
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//                dateString = sdf.format(tempstartdate);
//                modifiedString = dateString.substring(0, 11) + "09:16:00.000";
//                startdate = sdf.parse(modifiedString);
//                
//                enddate = callListTwoMonths.get(i1).getCalltablePK().getLastupdateminute();
//                
//                record.setScripId(callListTwoMonths.get(i1+1).getCalltablePK().getScripid());
//                record.setCallGenerationTimeStamp(callListTwoMonths.get(i1+1).getCalltablePK().getLastupdateminute());
//                record.setPrice(price);
//                record.setCallVersionOne(callone);               
//                record.setCallVersionTwo(calltwo);
//                
//                if((callone.equals("buy") && calltwo.equals("buy"))||
//                   (callone.equals("buy") && calltwo.equals("no"))||
//                   (callone.equals("no") && calltwo.equals("buy"))){
//                   double maxprice = mds.getBuyMaxMinutePrice(scrip, startdate, enddate);
//                   if(price<maxprice){
//                       calculatedMargin = maxprice;
//                   }
//                   if(price>maxprice){
//                       calculatedMargin = 0.00;
//                   }
//                   if(callone.equals("buy")){
//                      record.setMarginOne(calculatedMargin);
//                      if(calculatedMargin == 0.00){
//                          record.setOutcomeOne("fail");
//                      } else {
//                          record.setOutcomeOne("pass");
//                      }
//                   }
//                   if(calltwo.equals("buy")){
//                      record.setMarginTwo(calculatedMargin);
//                      if(calculatedMargin == 0.00){
//                          record.setOutcomeTwo("fail");
//                      } else {
//                          record.setOutcomeTwo("pass");
//                      }
//                   }
//                   
//                   if(callone.equals("no")){
//                       record.setMarginOne(0.00);
//                       record.setOutcomeOne("NA");
//                   }
//                   if(calltwo.equals("no")){
//                       record.setMarginTwo(0.00);
//                       record.setOutcomeTwo("NA");
//                   }
//                } // buy & buy,  buy & no,  no & buy
//                
//                if ((callone.equals("sell") && calltwo.equals("sell"))
//                        || (callone.equals("sell") && calltwo.equals("no"))
//                        || (callone.equals("no") && calltwo.equals("sell"))) {
//                    double minprice = mds.getSelMinMinutePrice(scrip, startdate, enddate);
//                    if (price > minprice) {
//                        calculatedMargin = minprice;
//                    }
//                    if (price < minprice) {
//                        calculatedMargin = 0.00;
//                    }
//                    if(callone.equals("sell")){
//                      record.setMarginOne(calculatedMargin);
//                      if(calculatedMargin == 0.00){
//                          record.setOutcomeOne("fail");
//                      } else {
//                          record.setOutcomeOne("pass");
//                      }
//                   }
//                   if(calltwo.equals("sell")){
//                      record.setMarginTwo(calculatedMargin);
//                      if(calculatedMargin == 0.00){
//                          record.setOutcomeTwo("fail");
//                      } else {
//                          record.setOutcomeTwo("pass");
//                      }
//                   }
//                   
//                   if(callone.equals("no")){
//                       record.setMarginOne(0.00);
//                       record.setOutcomeOne("NA");
//                   }
//                   if(calltwo.equals("no")){
//                       record.setMarginTwo(0.00);
//                       record.setOutcomeTwo("NA");
//                   }
//                } // sell & sell,  sell & no,  no & sell
//                
//                if ((callone.equals("buy") && calltwo.equals("sell"))
//                        || (callone.equals("sell") && calltwo.equals("buy"))) {
//                    double maxprice = mds.getBuyMaxMinutePrice(scrip, startdate,
//                            enddate);
//                    double minprice = mds.getSelMinMinutePrice(scrip, startdate,
//                            enddate);
//                    if (callone.equals("buy") || calltwo.equals("buy")) {
//                        if (price < maxprice) {
//                            calculatedMargin = maxprice;
//                        }
//                        if (price > maxprice) {
//                            calculatedMargin = 0.00;
//                        }
//                    }
//                    if(callone.equals("buy")){
//                      record.setMarginOne(calculatedMargin);
//                      if(calculatedMargin == 0.00){
//                          record.setOutcomeOne("fail");
//                      } else {
//                          record.setOutcomeOne("pass");
//                      }
//                   }
//                   if(calltwo.equals("buy")){
//                      record.setMarginTwo(calculatedMargin);
//                      if(calculatedMargin == 0.00){
//                          record.setOutcomeTwo("fail");
//                      } else {
//                          record.setOutcomeTwo("pass");
//                      }
//                   }
//                   
//                    if (callone.equals("sell") || calltwo.equals("sell")) {
//                        if (price > minprice) {
//                            calculatedMargin = minprice;
//                        }
//                        if (price < minprice) {
//                            calculatedMargin = 0.00;
//                        }
//                        if (callone.equals("sell")) {
//                            record.setMarginOne(calculatedMargin);
//                            if (calculatedMargin == 0.00) {
//                                record.setOutcomeOne("fail");
//                            } else {
//                                record.setOutcomeOne("pass");
//                            }
//                        }
//                        if (calltwo.equals("sell")) {
//                            record.setMarginTwo(calculatedMargin);
//                            if (calculatedMargin == 0.00) {
//                                record.setOutcomeTwo("fail");
//                            } else {
//                                record.setOutcomeTwo("pass");
//                            }
//                        }
//                    }
//                }// sell & buy
//                
//                if (callone.equals("no") && calltwo.equals("no")) {
//                    record.setMarginOne(0.00);
//                    record.setOutcomeOne("NA");
//                    record.setMarginTwo(0.00);
//                    record.setOutcomeTwo("NA");
//                } // no & no
//                //insert into validatecall
//                mds.insertValidateCall(record);
//            }
//        }
    }
}
