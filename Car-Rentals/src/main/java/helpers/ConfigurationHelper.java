package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public final class ConfigurationHelper {

    private ConfigurationHelper(){
    }

    public static String getConfig(String propName) throws IOException {
        var properties = new Properties();
        properties.load(new FileInputStream("F:\\lab6_HomeAssignmentLast\\src\\Cars.Properties"));
        properties.load(new FileInputStream("F:\\lab6_HomeAssignmentLast\\src\\rental_list.txt"));
        String propValue = properties.getProperty(propName);

        return propValue;


        /*try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("F:\\lab6_HomeAssignmentLast\\src\\Cars.Properties"));
            String carFileName=properties.getProperty("car_list");
            if (carFileName==null){ //the property does not exist in the file
                carFileName="ComputerRequests.txt";
                System.err.println("Requests file not found. Using default"+requestFileName);
            }
            String formsFileName=properties.getProperty("FormsFile");
            if (formsFileName==null){
                formsFileName="RepairedForms.txt";
                System.err.println("RepairedForms file not found. Using default"+formsFileName);
            }
            ComputerRepairRequestRepository crrRepo = newComputerRepairRequestFileRepository(requestFileName);
            ComputerRepairedFormFileRepository crfRepo = newComputerRepairedFormFileRepository(formsFileName, crrRepo);}
        catch (IOException ex){
                System.err.println("Error reading the configuration file"+ex);*/
        }
}
