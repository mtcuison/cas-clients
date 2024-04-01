package org.guanzon.clients;

import java.util.ArrayList;
import org.rmj.appdriver.GRider;
import org.rmj.appdriver.constants.EditMode;
import org.rmj.appdriver.iface.XMRecord;

/**
 *
 * @author Michael Cuison
 */
public class Client_Master implements XMRecord{
    GRider poAppDrver;
    String psBranchCd;
    boolean pbWtParent;
    
    int pnEditMode;
    String psMessagex;
    
    Model_Client_Master poClient;
    
    ArrayList<Model_Client_Mail> paMail;
    ArrayList<Model_Client_Mobile> paMobile;
    ArrayList<Model_Client_Address> paAddress;
    ArrayList<Model_Client_Social_Media> paSocMed;
    ArrayList<Model_Client_Institution_Contact> paInsContc;
    
    public Client_Master(GRider foAppDrver, boolean fbWtParent, String fsBranchCd){
        poAppDrver = foAppDrver;
        pbWtParent = fbWtParent;
        psBranchCd = fsBranchCd.isEmpty() ? foAppDrver.getBranchCode() : fsBranchCd;
    }

    @Override
    public int getEditMode() {
        return pnEditMode;
    }

    @Override
    public boolean newRecord() {
        pnEditMode = EditMode.ADDNEW;
        return true;
    }

    @Override
    public boolean openRecord(String fsValue) {
        pnEditMode = EditMode.READY;
        return true;
    }

    @Override
    public boolean updateRecord() {
        pnEditMode = EditMode.UPDATE;
        return true;
    }

    @Override
    public boolean saveRecord() {
        pnEditMode = EditMode.READY;
        return true;
    }

    @Override
    public boolean deactivateRecord(String fsValue) {
        pnEditMode = EditMode.READY;
        return true;
    }

    @Override
    public boolean activateRecord(String fsValue) {
        pnEditMode = EditMode.READY;
        return true;
    }
    
    public boolean addContact(){
        if (paMobile.isEmpty()){
            paMobile.add(new Model_Client_Mobile(poAppDrver.getConnection()));
        } else {
            if (paMobile.get(paMobile.size()-1).getContactNo().isEmpty()){
                paMobile.add(new Model_Client_Mobile(poAppDrver.getConnection()));
            } else {
                psMessagex = "Last contact information has no contact number.";
                return false;
            }
        }
        
        return true;
    }
    
    public Model_Client_Mobile getContact(int fnIndex){
        if (fnIndex > paMobile.size() - 1 || fnIndex < 0) return null;
        
        return paMobile.get(fnIndex);
    }
    
    //unused implemented methods
    @Override
    public boolean deleteRecord(String fsValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void setBranch(String foBranchCD) {        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaster(int fnCol, Object foData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMaster(String fsCol, Object foData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getMaster(int fnCol) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getMaster(String fsCol) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}