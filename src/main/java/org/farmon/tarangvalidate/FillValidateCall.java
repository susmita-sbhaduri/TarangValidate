/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.farmon.tarangvalidate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.bhaduri.tarangcall.scrips.Scrips;
import org.bhaduri.tarangdbservice.entities.Calltable;
import org.bhaduri.tarangdbservice.entities.Validatecall;
import org.bhaduri.tarangdbservice.services.MasterDataServices;
import org.bhaduri.tarangdto.ScripsDTO;
import org.bhaduri.tarangdto.ValidateCallRec;

/**
 *
 * @author sb
 */
public class FillValidateCall {
    private int noOfResults;
    
    public FillValidateCall(int noOfResults) {
        this.noOfResults = noOfResults;
    }
    
    public void updateValidatecall() throws ParseException {
        List<ScripsDTO> scriplist = new Scrips().getScripList();
        double price;
        double calculatedMargin = 0;
        String callone;
        String calltwo;        
        String scrip;  
        Date tempstartdate;
        String modifiedString;
        Date startdate;
        String dateString;
        
        Date enddate;
        MasterDataServices mds = new MasterDataServices();
        Validatecall oldestrec = new Validatecall();
        for (int i = 0; i < scriplist.size(); i++) {            
            List<Calltable> callList = mds.getValidateCallsForDuration
        (scriplist.get(i).getScripId(), noOfResults);
            scrip= scriplist.get(i).getScripId();
            for (int i1 = 0; i1 < callList.size()-1; i1++){
                
                ValidateCallRec record = new ValidateCallRec();
                price = callList.get(i1+1).getPrice();
                callone = callList.get(i1+1).getCallone();
                calltwo = callList.get(i1+1).getCalltwo();
                
                tempstartdate = callList.get(i1).getCalltablePK().getLastupdateminute();                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                dateString = sdf.format(tempstartdate);
                modifiedString = dateString.substring(0, 11) + "09:16:00.000";
                startdate = sdf.parse(modifiedString);
                
                enddate = callList.get(i1).getCalltablePK().getLastupdateminute();
                
                record.setScripId(callList.get(i1+1).getCalltablePK().getScripid());
                record.setCallGenerationTimeStamp(callList.get(i1+1).getCalltablePK().getLastupdateminute());
                record.setPrice(price);
                record.setCallVersionOne(callone);               
                record.setCallVersionTwo(calltwo);
                
                if((callone.equals("buy") && calltwo.equals("buy"))||
                   (callone.equals("buy") && calltwo.equals("no"))||
                   (callone.equals("no") && calltwo.equals("buy"))){
                   double maxprice = mds.getBuyMaxMinutePrice(scrip, startdate, enddate);
                   if(price<maxprice){
                       calculatedMargin = maxprice;
                   }
                   if(price>maxprice){
                       calculatedMargin = 0.00;
                   }
                   if(callone.equals("buy")){
                      record.setMarginOne(calculatedMargin);
                      if(calculatedMargin == 0.00){
                          record.setOutcomeOne("fail");
                      } else {
                          record.setOutcomeOne("pass");
                      }
                   }
                   if(calltwo.equals("buy")){
                      record.setMarginTwo(calculatedMargin);
                      if(calculatedMargin == 0.00){
                          record.setOutcomeTwo("fail");
                      } else {
                          record.setOutcomeTwo("pass");
                      }
                   }
                   
                   if(callone.equals("no")){
                       record.setMarginOne(0.00);
                       record.setOutcomeOne("NA");
                   }
                   if(calltwo.equals("no")){
                       record.setMarginTwo(0.00);
                       record.setOutcomeTwo("NA");
                   }
                } // buy & buy,  buy & no,  no & buy
                
                if ((callone.equals("sell") && calltwo.equals("sell"))
                        || (callone.equals("sell") && calltwo.equals("no"))
                        || (callone.equals("no") && calltwo.equals("sell"))) {
                    double minprice = mds.getSelMinMinutePrice(scrip, startdate, enddate);
                    if (price > minprice) {
                        calculatedMargin = minprice;
                    }
                    if (price < minprice) {
                        calculatedMargin = 0.00;
                    }
                    if(callone.equals("sell")){
                      record.setMarginOne(calculatedMargin);
                      if(calculatedMargin == 0.00){
                          record.setOutcomeOne("fail");
                      } else {
                          record.setOutcomeOne("pass");
                      }
                   }
                   if(calltwo.equals("sell")){
                      record.setMarginTwo(calculatedMargin);
                      if(calculatedMargin == 0.00){
                          record.setOutcomeTwo("fail");
                      } else {
                          record.setOutcomeTwo("pass");
                      }
                   }
                   
                   if(callone.equals("no")){
                       record.setMarginOne(0.00);
                       record.setOutcomeOne("NA");
                   }
                   if(calltwo.equals("no")){
                       record.setMarginTwo(0.00);
                       record.setOutcomeTwo("NA");
                   }
                } // sell & sell,  sell & no,  no & sell
                
                if ((callone.equals("buy") && calltwo.equals("sell"))
                        || (callone.equals("sell") && calltwo.equals("buy"))) {
                    double maxprice = mds.getBuyMaxMinutePrice(scrip, startdate,
                            enddate);
                    double minprice = mds.getSelMinMinutePrice(scrip, startdate,
                            enddate);
                    if (callone.equals("buy") || calltwo.equals("buy")) {
                        if (price < maxprice) {
                            calculatedMargin = maxprice;
                        }
                        if (price > maxprice) {
                            calculatedMargin = 0.00;
                        }

                        if (callone.equals("buy")) {
                            record.setMarginOne(calculatedMargin);
                            if (calculatedMargin == 0.00) {
                                record.setOutcomeOne("fail");
                            } else {
                                record.setOutcomeOne("pass");
                            }
                        }
                        if (calltwo.equals("buy")) {
                            record.setMarginTwo(calculatedMargin);
                            if (calculatedMargin == 0.00) {
                                record.setOutcomeTwo("fail");
                            } else {
                                record.setOutcomeTwo("pass");
                            }
                        }
                    }
                    if (callone.equals("sell") || calltwo.equals("sell")) {
                        if (price > minprice) {
                            calculatedMargin = minprice;
                        }
                        if (price < minprice) {
                            calculatedMargin = 0.00;
                        }
                        if (callone.equals("sell")) {
                            record.setMarginOne(calculatedMargin);
                            if (calculatedMargin == 0.00) {
                                record.setOutcomeOne("fail");
                            } else {
                                record.setOutcomeOne("pass");
                            }
                        }
                        if (calltwo.equals("sell")) {
                            record.setMarginTwo(calculatedMargin);
                            if (calculatedMargin == 0.00) {
                                record.setOutcomeTwo("fail");
                            } else {
                                record.setOutcomeTwo("pass");
                            }
                        }
                    }
                }// sell & buy
                
                if (callone.equals("no") && calltwo.equals("no")) {
                    record.setMarginOne(0.00);
                    record.setOutcomeOne("NA");
                    record.setMarginTwo(0.00);
                    record.setOutcomeTwo("NA");
                } // no & no
                //insert into validatecall
                mds.insertValidateCall(record);
            }
        }
        
        if(noOfResults ==2){
            oldestrec = mds.getOldestValidatecallRec();
            int deletedCount = mds.deleteOldestValidatecallRec(oldestrec);
            System.out.println("Number of records deleted = " + deletedCount);
        }
    }

    public int getNoOfResults() {
        return noOfResults;
    }

    public void setNoOfResults(int noOfResults) {
        this.noOfResults = noOfResults;
    }
    
    
}
