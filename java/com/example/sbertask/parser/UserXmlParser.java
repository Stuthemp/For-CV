package com.example.sbertask.parser;

import android.util.Log;

import com.example.sbertask.model.Valute;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class UserXmlParser {

    public ArrayList<Valute> users;

    public UserXmlParser(){
        users = new ArrayList<>();
    }

    public ArrayList<Valute> getUsers(){
        return  users;
    }

    public boolean parse(String xmlData){
        boolean status = true;
        Valute currentUser = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(xmlData));
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("Valute".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentUser = new Valute();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("Valute".equalsIgnoreCase(tagName)){
                                users.add(currentUser);
                                Log.v("Checking parser",currentUser.getCharCode());

                                inEntry = false;
                            } else if("CharCode".equalsIgnoreCase(tagName)){
                                currentUser.setCharCode(textValue);
                            } else if("Value".equalsIgnoreCase(tagName)){
                                currentUser.setRate(textValue);
                            } else if("Name".equalsIgnoreCase(tagName)){
                                currentUser.setName(textValue);
                            } else if("Nominal".equalsIgnoreCase(tagName)){
                                currentUser.setNominal((textValue));
                            } else if("NumCode".equalsIgnoreCase(tagName)){
                                currentUser.setId((textValue));
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }
        Log.v("Checking parser", String.valueOf(users.size()));
        return  status;
    }
}