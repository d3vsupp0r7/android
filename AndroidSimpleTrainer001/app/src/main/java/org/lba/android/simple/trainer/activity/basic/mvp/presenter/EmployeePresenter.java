package org.lba.android.simple.trainer.activity.basic.mvp.presenter;

import org.lba.android.simple.trainer.activity.basic.mvp.model.EmployeeMVPSampleModel;

public class EmployeePresenter {

    private EmployeeMVPSampleModel employeeDataModel;
    private View view;

    public EmployeePresenter(View view) {
        this.employeeDataModel = new EmployeeMVPSampleModel();
        this.view = view;
    }

    public void updateName(String name){
        employeeDataModel.setName(name);
        view.updateUserInfoTextView(employeeDataModel.toString());

    }

    public void updateSurname(String surname){
        employeeDataModel.setSurname(surname);
        view.updateUserInfoTextView(employeeDataModel.toString());

    }

    public interface View{

        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();

    }
}
