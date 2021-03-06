package com.mysema.stat.pcaxis;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;


public class DefaultDatasetHandlerTest {

    @Test
    public void Parse() throws IOException{
        DefaultDatasetHandler handler = new DefaultDatasetHandler();
        PCAxisParser parser = new PCAxisParser(handler);
        parser.parse("A01HKIS_Vaestotulot", getClass().getResourceAsStream("/A01HKIS_Vaestotulot.px"));
        for (Item item : handler.getItems("A01HKIS_Vaestotulot")){
            String str = item.getDimensions().toString();
            if (str.contains("yhteens")){
                assertTrue(str, str.contains("yhteens\u00E4"));
            }
        }
    }

}
