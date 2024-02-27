package geocoding;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.when;
import java.util.Optional; // Add this import statement

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {

    @Mock
    ISimpleHttpClient httpClient;

    @InjectMocks
    AddressResolverService resolver;

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException {

        //todo: implement test; remove Disabled annotation

        // Copiei a resposta exemplo, e apenas substitui os campos que são relevantes para o teste
        // PS: Notei agora que podia simplesmente ter feito um request e ter obtido os dados corretos, que seria a forma mais adequada

        //when(httpClient.doHttpGet(startsWith("https://www.mapquestapi.com/geocoding/v1/reverse?key=zrg8ntcCCcUuWflmDVKBemPVJfiVy6kg&location=40.63436,-8.65616")))
        when(httpClient.doHttpGet(anyString()))
                .thenReturn("{" +
                        "\"info\": {" +
                            "\"statuscode\": 0," +
                            "\"copyright\": {" +
                                "\"text\": \"© 2024 MapQuest, Inc.\"," +
                                "\"imageUrl\": \"https://api.mqcdn.com/res/mqlogo.gif\"," +
                                "\"imageAltText\": \"© 2024 MapQuest, Inc.\"" +
                            "}," +
                            "\"messages\": []" +
                        "}," +
                        "\"options\": {" +
                            "\"maxResults\": 1," +
                            "\"thumbMaps\": true," +
                            "\"ignoreLatLngInput\": false" +
                        "}," +
                        "\"results\": [" +
                            "{" +
                                "\"providedLocation\": {" +
                                    "\"latLng\": {" +
                                        "\"lat\": 40.63436," +
                                        "\"lng\": -8.65616" +
                                    "}" +
                                "}," +
                                "\"locations\": [" +
                                    "{" +
                                        "\"street\": \"Avenida da Universidade\"," + // <--- road
                                        "\"adminArea6\": \"\"," +
                                        "\"adminArea6Type\": \"Neighborhood\"," +
                                        "\"adminArea5\": \"Aveiro\"," + // <--- city
                                        "\"adminArea5Type\": \"City\"," +
                                        "\"adminArea4\": \"Duval\"," +
                                        "\"adminArea4Type\": \"County\"," +
                                        "\"adminArea3\": \"FL\"," +
                                        "\"adminArea3Type\": \"State\"," +
                                        "\"adminArea1\": \"US\"," +
                                        "\"adminArea1Type\": \"Country\"," +
                                        "\"postalCode\": \"3810-489\"," + // <--- zip
                                        "\"geocodeQualityCode\": \"L1AAA\"," +
                                        "\"geocodeQuality\": \"ADDRESS\"," +
                                        "\"dragPoint\": false," +
                                        "\"sideOfStreet\": \"R\"," +
                                        "\"linkId\": \"0\"," +
                                        "\"unknownInput\": \"\"," +
                                        "\"type\": \"s\"," +
                                        "\"latLng\": {" +
                                            "\"lat\": 40.63436," +
                                            "\"lng\": -8.65616" +
                                        "}," +
                                        "\"displayLatLng\": {" +
                                            "\"lat\": 40.63436," +
                                            "\"lng\": -8.65616" +
                                        "}," +
                                        "\"mapUrl\": \"https://www.mapquestapi.com/staticmap/v4/getmap?key=KEY&type=map&size=225,160&pois=purple-1,30.3334721,-81.4704483,0,0,|&center=30.3334721,-81.4704483&zoom=15&rand=-553163060\"," +
                                        "\"nearestIntersection\": {" +
                                            "\"streetDisplayName\": \"Posey Cir\"," +
                                            "\"distanceMeters\": \"851755.1608527573\"," +
                                            "\"latLng\": {" +
                                                "\"longitude\": -87.523761," +
                                                "\"latitude\": 35.013434" +
                                            "}," +
                                            "\"label\": \"Danley Rd & Posey Cir\"" +
                                        "}," +
                                        "\"roadMetadata\": {" +
                                            "\"speedLimitUnits\": \"mph\"," +
                                            "\"tollRoad\": null," +
                                            "\"speedLimit\": 40" +
                                        "}" +
                                    "}" +
                                "]" +
                            "}" +
                        "]" +
                    "}");

        // will crash for now...need to set the resolver before using it
        Optional<Address> result = resolver.findAddressForLocation(40.63436, -8.65616);

        //return
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        ///todo: implement test
        when(httpClient.doHttpGet(anyString()))
                .thenReturn("{\"info\":{\"statuscode\":400,\"copyright\":{\"text\":\"© 2024 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"© 2024 MapQuest, Inc.\"},\"messages\":[\"Illegal argument from request: Invalid LatLng specified.\"]},\"options\":{\"maxResults\":1,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{},\"locations\":[]}]}");

        Optional<Address> result = resolver.findAddressForLocation(-361, -361); // Add missing closing parenthesis
        // verify no valid result
        assertFalse(result.isPresent());

    }
}