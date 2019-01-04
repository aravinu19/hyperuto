package MainApp.handlers;

import com.profesorfalken.jpowershell.PowerShell;
import com.profesorfalken.jpowershell.PowerShellNotAvailableException;
import com.profesorfalken.jpowershell.PowerShellResponse;

import java.util.ArrayList;

public class PowerCLIHandler {

    String formatterProperty = " | Format-Table -Property ";

    public boolean Connect_To_Hypervisor(String host_address, String username, String password){

        try (PowerShell powerShell = PowerShell.openSession()){

            String ConnectionString = "Connect-VIServer -Server " + host_address + " -User " + username + " -Password " + password + formatterProperty + "User" ;

            PowerShellResponse response = powerShell.executeCommand(ConnectionString);

            System.out.println(response.isError());

            if(response.getCommandOutput().contains("root")){
                return true;
            }else{
                return false;
            }

        }catch (PowerShellNotAvailableException exception){
            System.out.println(exception.getMessage());
            return false;
        }

    }

    public String Get_VM_List(String host_address, String username, String password){

        try(PowerShell powerShell = PowerShell.openSession()){

            String ConnectionString = "Connect-VIServer -Server " + host_address + " -User " + username + " -Password " + password + formatterProperty + "User" ;
            String vm_list_command = "Get-VM" + formatterProperty + "Name, PowerState";

            powerShell.executeCommand(ConnectionString);
            PowerShellResponse response = powerShell.executeCommand(vm_list_command);

            String output[] = response.getCommandOutput().split("\r\n");

            ArrayList<String> expected_output = new ArrayList<>();

            for (String item : output) {
                if(item.contains("Name")) continue;
                if(item.contains("-")) continue;
                if(item.equals("")) continue;

                expected_output.add(item);
            }

            System.out.println(expected_output);

            return response.getCommandOutput();

        }catch (PowerShellNotAvailableException e){
            System.out.println(e.getMessage());
        }

        return "Done";
    }

    public String Shutdown_VM(){
        return "";
    }

    public String Enter_Maintanance_Mode(){
        return "";
    }

}
