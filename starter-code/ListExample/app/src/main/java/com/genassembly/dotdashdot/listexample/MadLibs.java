package com.genassembly.dotdashdot.listexample;

import java.util.ArrayList;

public class MadLibs {

    private int words, spaces;
    private String genre;
    static int wordCount=0;
    public ArrayList<String> madLibs;

    public MadLibs(String genre, int words, int spaces){
        this.genre = genre;
        this.words = words;
        this.spaces = spaces;
    }
<<<<<<< HEAD

    public MadLibs(String genre, ArrayList<String> madLib){
=======
    public MadLibs(String genre, ArrayList<String> madLibs){
>>>>>>> b4049600169fc950f3f4be1ecdd236246770c864
        this.genre = genre;
        this.madLibs = madLibs;
        this.words = getWordsCount(this.madLibs);
        this.spaces = getSpaceCount(this.madLibs);

    }


    public int getWordsCount(ArrayList<String>madLib){
        for(int i=0;i<=madLib.size();i+=2){
            String s =madLib.get(i);
            char ch[]=new char[s.length()];//instringespeciallywehavetomentionthe()afterlength
            for(i=0;i<s.length();i++)
            {
                ch[i]=s.charAt(i);
                if(((i>0)&&(ch[i]!=' ')&&(ch[i-1]==' '))||((ch[0]!=' ')&&(i==0))) {
                    wordCount++;
                }
            }
        }

        return wordCount;
    }

    public int getSpaceCount(ArrayList<String>madLib){
        spaces=madLib.size()/2;
        return spaces;
    }
    public int getWords() {
        return words;
    }

    public int getSpaces() {
        return spaces;
    }

    public String getGenre() {
        return genre;
    }

}
