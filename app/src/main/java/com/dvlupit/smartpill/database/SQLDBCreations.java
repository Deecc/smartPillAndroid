package com.dvlupit.smartpill.database;



/**
 * Created by deck on 18/08/15.
 */
public class SQLDBCreations {

    public static final String TBL_AVAILABILITY = "Availability";
    public static final String TBL_CATEGORY = "Category";
    public static final String TBL_CONCENTRATION = "Concentration";
    public static final String TBL_ACTIVEINGREDIENT = "Active_Ingredient";
    public static final String TBL_MANUFACTURER = "Manufacturer";
    public static final String TBL_MEDICINE = "Medicine";
    public static final String TBL_REFER = "Reference";
    public static final String TBL_REMINDER = "ReminderScheduleActivity";
    public static final String TBL_REMINDER_SOUND = "ReminderSound";
    public static final String TBL_REMINDER_SCHEDULE = "Reminder_schedule";
    public static final String TBL_USER = "Users";
    public static final String TBL_USER_MEDICINE = "UsersMedicine";
    public static final String TBL_RECIPE = "Recipe";
    public static final String TBL_RECIPE_MEDICINE = "RecipeMedicine";



    //Tabela AC
    public static final String ID_AI = "_id";
    public static final String AI = "activeIngredient";
    //Tabela AV
    public static final String ID_AV = "_id";
    public static final String AV = "availability";
    //Tabela CAT
    public static final String ID_CAT = "_id";
    public static final String CAT = "category";
    //Tabela CON
    public static final String ID_CON = "_id";
    public static final String CON = "concentration";
    //Tabela Manu
    public static final String ID_MANU = "_id";
    public static final String MANU = "manufacturer";
    //Tabela Med
    public static final String ID_MED = "_id";
    public static final String MED_NAME = "name";
    public static final String MED_QTD = "quantity";
    public static final String MED_ID_AI = "idActiveIngredient";
    public static final String MED_ID_AV = "idAvailability";
    public static final String MED_ID_CAT = "idCategory";
    public static final String MED_ID_CON= "idConcentration";
    public static final String MED_ID_MANU = "idManufacturer";
    public static final String MED_ID_REFER = "idReference";

    //Tabela REFER
    public static final String ID_REFER = "_id";
    public static final String REFER = "reference";

    //Tabela REM
    public static final String ID_REM = "_id";
    public static final String ID_REMINDER_SCHEDULE = "idReminderSchedule";
    public static final String ID_REM_SOUND = "idReminderSound";
    public static final String ID_REM_MEDICINE = "idMedicine";

    //TABELA REM_SCHEDULE
    public static final String ID_REM_SCHEDULE = "_id";
    public static final String REM_SCHEDULE = "schedule";
    public static final String REM_STARTDATE = "startDate";
    public static final String REM_ENDDATE = "endDate";
    //Tabela Reminder_sound
    public static final String ID_REMINDER_SOUND = "_id";
    public static final String SOUND = "sound";

    //Tabela USER
    public static final String ID_USER = "_id";
    public static final String USER_NAME = "name";
    public static final String USER_PASS = "pass";
    public static final String USER_EMAIL = "email";
    //TABELA USER_MED
    public static final String ID_USER_MEDICINE_USER = "idUsers";
    public static final String ID_USER_MEDICINE_MEDICINE = "idMedicine";
    public static final String USER_MEDICINE_EXP_DATE = "expiryDate";
    public static final String USER_MEDICINE_QTD = "quantity";

    //TABELA RECIPE
    public static final String ID_RECIPE = "_id";
    public static final String RECIPE_IMAGE = "recipeImage";
    //TABELA RECIPE_MED
    public static final String RECIPE_MED = "idMedicine";
    public static final String RECIPE_ID = "idRecipe";



    private String createActiveIngredient;
    private String createAvailability;
    private String createCategory;
    private String createConcentration;
    private String createManufacturer;
    private String createMedicine;
    private String createRefer;
    private String createReminder;
    private String createReminderSchedule;
    private String createReminderSound;
    private String createUser;
    private String createUserMedicine;
    private String createRecipe;
    private String createRecipeMedicine;

    public String createAI(){
        createActiveIngredient = "CREATE TABLE "+TBL_ACTIVEINGREDIENT+"("
                + ID_AI + " integer primary key autoincrement,"
                + AI + " text"
                +")";
        return createActiveIngredient;
    }

    public String createAV(){
        createAvailability = "CREATE TABLE "+ TBL_AVAILABILITY+"("
                + ID_AV + " integer primary key autoincrement,"
                + AV + "text"
                +")";
        return createAvailability;
    }

    public String createCAT(){
        createCategory = "CREATE TABLE "+ TBL_CATEGORY+"("
                + ID_CAT + " integer primary key autoincrement,"
                + CAT + "text"
                +")";
        return createCategory;
    }

    public String createCON(){
        createConcentration = "CREATE TABLE "+ TBL_CONCENTRATION+"("
                + ID_CON + " integer primary key autoincrement,"
                + CON + "text"
                +")";
        return createConcentration;
    }

    public String createMANU(){
        createManufacturer = "CREATE TABLE "+ TBL_MANUFACTURER+"("
                + ID_MANU + " integer primary key autoincrement,"
                + MANU + "text"
                +")";
        return createManufacturer;
    }

    public String createREFER(){
        createRefer = "CREATE TABLE "+ TBL_REFER+"("
                + ID_REFER + " integer primary key autoincrement,"
                + REFER + "text"
                +")";
        return createRefer;
    }

    public String createMED(){
        createMedicine = "CREATE TABLE "+ TBL_MEDICINE+"("
                + ID_MED + " integer primary key autoincrement,"
                + MED_NAME + "text,"
                + MED_QTD + "text,"
                + MED_ID_AI + "integer,"
                + MED_ID_AV + "integer,"
                + MED_ID_CAT + "integer,"
                + MED_ID_CON + "integer,"
                + MED_ID_MANU + "integer,"
                + MED_ID_REFER + "integer,"
                + " FOREIGN KEY ("+MED_ID_AI+") REFERENCES "+TBL_ACTIVEINGREDIENT+" ("+ID_AI+"),"
                + " FOREIGN KEY ("+MED_ID_AV+") REFERENCES "+TBL_AVAILABILITY+" ("+ID_AV+"),"
                + " FOREIGN KEY ("+MED_ID_CAT+") REFERENCES "+TBL_CATEGORY+" ("+ID_CAT+"),"
                + " FOREIGN KEY ("+MED_ID_CON+") REFERENCES "+TBL_CONCENTRATION+" ("+ID_CON+"),"
                + " FOREIGN KEY ("+MED_ID_MANU+") REFERENCES "+TBL_MANUFACTURER+" ("+ID_MANU+"),"
                + " FOREIGN KEY ("+MED_ID_REFER+") REFERENCES "+TBL_REFER+" ("+ID_REFER+"))";
        return createMedicine;
    }

    public String createREM(){
        createReminder = "CREATE TABLE "+TBL_REMINDER+ "("
                + ID_REM + "integer primary key autoincrement,"
                +ID_REM_SCHEDULE+ "integer,"
                +ID_REM_SOUND + "integer,"
                +ID_REM_MEDICINE + "integer,"
                + " FOREIGN KEY ("+ID_REM_MEDICINE+") REFERENCES "+TBL_MEDICINE+" ("+ID_MED+"),"
                + " FOREIGN KEY ("+ID_REM_SOUND+") REFERENCES "+TBL_REMINDER_SOUND+" ("+ID_REMINDER_SOUND+"),"
                + " FOREIGN KEY ("+ID_REM_SCHEDULE+") REFERENCES "+TBL_REMINDER_SCHEDULE+" ("+ID_REMINDER_SCHEDULE+")"
                +")";
        return createReminder;
    }
    public String createReminderSchedule(){
        createReminderSchedule = "CREATE TABLE " + TBL_REMINDER_SCHEDULE + "("
                + ID_REM_SCHEDULE + "integer primary key autoincrement,"
                + REM_SCHEDULE + "time,"
                + REM_STARTDATE + "date,"
                + REM_ENDDATE + "date"
                +")";
        return createReminderSchedule;
    }
    public String createReminderSound(){
        createReminderSound = "CREATE TABLE " + TBL_REMINDER_SOUND + "("
                + ID_REMINDER_SOUND + "integer primary key autoincrement,"
                + SOUND + "blob"
                +")";
        return createReminderSound;
    }

    public String createUser(){
        createUser = "CREATE TABLE "+TBL_USER+"("
                + ID_USER + "integer primary key autoincrement,"
                + USER_NAME +"text,"
                + USER_EMAIL+ "text,"
                + USER_PASS + "text"
                +")";
        return createUser;

    }

    public String createUserMedicine(){
        createUserMedicine = "CREATE TABLE "+TBL_USER_MEDICINE+ "("
                + ID_USER_MEDICINE_USER + "integer,"
                + ID_USER_MEDICINE_MEDICINE+ "integer,"
                + USER_MEDICINE_EXP_DATE + "date,"
                + USER_MEDICINE_QTD + "integer,"
                + " FOREIGN KEY ("+ID_USER_MEDICINE_USER+") REFERENCES "+TBL_USER+" ("+ID_USER+"),"
                + " FOREIGN KEY ("+ID_USER_MEDICINE_MEDICINE+") REFERENCES "+TBL_MEDICINE+" ("+ID_MED+"),"
                +")";
        return createUserMedicine;
    }

    public String createRecipe(){
        createRecipe = "CREATE TABLE "+TBL_RECIPE+"("
                + ID_RECIPE + "integer primary key autoincrement,"
                + RECIPE_IMAGE + "blob"
                +")";
        return createRecipe;
    }
    public String createRecipeMedincine(){
        createRecipeMedicine = "CREATE TABLE "+TBL_RECIPE_MEDICINE+"("
                + RECIPE_MED + "integer,"
                + RECIPE_ID  + "integer,"
                + " FOREIGN KEY ("+RECIPE_MED+") REFERENCES "+TBL_MEDICINE+" ("+ID_MED+"),"
                + " FOREIGN KEY ("+RECIPE_ID+") REFERENCES "+TBL_RECIPE+" ("+ID_RECIPE+")"
                +")";
        return createRecipeMedicine;
    }

}
