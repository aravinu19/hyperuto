package MainApp.Controllers;

import MainApp.DTOs.CreateVM_DTO;
import MainApp.DTOs.CredentialsDTO;
import MainApp.handlers.PowerCLIHandler;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/VM_API", method = RequestMethod.POST)
public class ApiController {

    @Autowired private WelcomeService service;
    @Autowired private InsideHypervisor hypervisorControls;
    @Autowired private Credentials credsLoad;

    CredentialsDTO credentialsObject;

    @GetMapping("/hello")
    public String hello(){
        return service.Message();
    }

    @RequestMapping(value = "/CreateVM", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> Create_VM(@RequestBody CreateVM_DTO CreateVMDataObj){
        return new ResponseEntity<Object>(hypervisorControls.CreateVM(CreateVMDataObj), HttpStatus.OK);
    }

    @RequestMapping(value = "/Connect", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> ConnectHypervisor(@RequestBody CredentialsDTO CredentialsDataObj){
        credentialsObject = CredentialsDataObj;
        return credsLoad.ConnectCredentials(CredentialsDataObj);
    }

    @RequestMapping(value = "/GetVMs", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> GetVms(){
        return hypervisorControls.GetVMs(credentialsObject);
    }

}


@Component
class WelcomeService {

    public String Message() {
        return "Hello From Server";
    }

}

@Component
class InsideHypervisor {

    PowerCLIHandler powerCLIHandler = new PowerCLIHandler();

    Map<String, String> GetVMs(CredentialsDTO data_Object){

        String result = powerCLIHandler.Get_VM_List(data_Object.getHost_address(), data_Object.getUsername(), data_Object.getPassword());

        HashMap<String, String> response = new HashMap<>();
        response.put("operation", result);

        return response;

    }


    Map<String, String> CreateVM(CreateVM_DTO data_Object){

        HashMap<String, String> response = new HashMap<>();
        response.put("Name", data_Object.getName());
        response.put("Memory", data_Object.getMemory());
        response.put("DiskSpace", data_Object.getDisk_storage());

        return response;
    }

    public String DeleteVM(){
        return "";
    }

}

@Component
class Credentials {

    PowerCLIHandler powerCLIHandler;

    Map<String, String> ConnectCredentials(CredentialsDTO data_Object){

        powerCLIHandler = new PowerCLIHandler();
        boolean operation = powerCLIHandler.Connect_To_Hypervisor( data_Object.host_address, data_Object.getUsername(), data_Object.getPassword());

        HashMap<String, String> response = new HashMap<>();
        response.put("operation", (operation) ? "Success" : "Failed");

        return response;
    }

}