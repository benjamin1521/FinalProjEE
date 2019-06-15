package ua.training.model.entities;

import ua.training.model.entities.enums.Status;

public class Report {

    private Long id;
    private Status status;
    private User clientId;
    private User inspectorId;
    private String name;

    private String code;
    private String name_short;
    private String address;
    private String phone;
    private String payment_name;
    private String oktmo;
    private String inn;
    private String kpp;
    private String bank_name;
    private String bank_bic;
    private String bank_account;
    private String parent_code;
    private String parent_name;
    private String parent_address;
    private String parent_phone;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public User getClientId() {
        return clientId;
    }

    public User getInspectorId() {
        return inspectorId;
    }

    public String getAddress() {
        return address;
    }

    public String getCode() {
        return code;
    }

    public String getName_short() {
        return name_short;
    }

    public String getBank_account() {
        return bank_account;
    }

    public String getInn() {
        return inn;
    }

    public String getBank_bic() {
        return bank_bic;
    }

    public String getBank_name() {
        return bank_name;
    }

    public String getKpp() {
        return kpp;
    }

    public String getOktmo() {
        return oktmo;
    }

    public String getParent_address() {
        return parent_address;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getParent_code() {
        return parent_code;
    }

    public String getParent_name() {
        return parent_name;
    }

    public String getParent_phone() {
        return parent_phone;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public static Builder newBuilder() {
        return new Report().new Builder();
    }

    public Builder builder() {
        return new Builder();
    }
    
    public class Builder{
        private Builder(){}


        public Builder id(Long id) {
            Report.this.id = id;
            return this;
        }

        public Builder name(String name) {
            Report.this.name = name;
            return this;
        }

        public Builder address(String address) {
            Report.this.address = address;
            return this;
        }

        public Builder clientId(User clientId) {
            Report.this.clientId = clientId;
            return this;
        }

        public Builder bank_account(String bank_account) {
            Report.this.bank_account = bank_account;
            return this;
        }

        public Builder code(String code) {
            Report.this.code = code;
            return this;
        }

        public Builder inspectorId(User inspectorId) {
            Report.this.inspectorId = inspectorId;
            return this;
        }

        public Builder status(Status status) {
            Report.this.status = status;
            return this;
        }

        public Builder bank_bic(String bank_bic) {
            Report.this.bank_bic = bank_bic;
            return this;
        }

        public Builder name_short(String name_short) {
            Report.this.name_short = name_short;
            return this;
        }

        public Builder inn(String inn) {
            Report.this.inn = inn;
            return this;
        }

        public Builder oktmo(String oktmo) {
            Report.this.oktmo = oktmo;
            return this;
        }

        public Builder bank_name(String bank_name) {
            Report.this.bank_name = bank_name;
            return this;
        }

        public Builder payment_name(String payment_name) {
            Report.this.payment_name = payment_name;
            return this;
        }

        public Builder kpp(String kpp) {
            Report.this.kpp = kpp;
            return this;
        }

        public Builder phone(String phone) {
            Report.this.phone = phone;
            return this;
        }

        public Builder parent_address(String parent_address) {
            Report.this.parent_address = parent_address;
            return this;
        }

        public Builder parent_code(String parent_code) {
            Report.this.parent_code = parent_code;
            return this;
        }

        public Builder parent_name(String parent_name) {
            Report.this.parent_name = parent_name;
            return this;
        }

        public Builder parent_phone(String parent_phone) {
            Report.this.parent_phone = parent_phone;
            return this;
        }

        public Report build(){
            return Report.this;
        }
        
    }
    

}
