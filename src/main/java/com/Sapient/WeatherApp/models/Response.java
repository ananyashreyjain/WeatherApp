package com.Sapient.WeatherApp.models;

public class Response {
 
    public final String[] body;

    public Response(final String[] body){
        this.body = body.clone();
    }

    public String toString(){
        String print = "Response:{\n";
        for(int i=0;i<this.body.length;i++){
            if(this.body[i]!=null){
                print += "\t" + this.body[i] + ", \n";
            }
        }
        print += "}\n";
        return print;
    }
}
