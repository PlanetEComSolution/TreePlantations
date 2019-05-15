package com.planet.treeplantations.rest;

import com.planet.treeplantations.models.AllTreePlantDetail;
import com.planet.treeplantations.models.All_Project_Model;
import com.planet.treeplantations.models.Budget_Model;
import com.planet.treeplantations.models.Dash_Main_Model;
import com.planet.treeplantations.models.Details_Responce_Model;
import com.planet.treeplantations.models.Devision_Name_Model;
import com.planet.treeplantations.models.Land_Type_Model;
import com.planet.treeplantations.models.Login_Model;
import com.planet.treeplantations.models.PO_MO_Model;
import com.planet.treeplantations.models.Project_Code_Model;
import com.planet.treeplantations.models.Project_Details_Model;
import com.planet.treeplantations.models.Project_Model;
import com.planet.treeplantations.models.Range_Model;
import com.planet.treeplantations.models.Region_Model;
import com.planet.treeplantations.models.Search_Model;
import com.planet.treeplantations.models.State_Model;
import com.planet.treeplantations.models.Tree_Species_Model;
import com.planet.treeplantations.models.Year_Maintence_Model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Interface {

    @GET("Login")
    Call<Login_Model> login(@Query("id") String username, @Query("pwd") String password);

    @GET("ChangePassword")
    Call<ResponseBody> changePassword(@Query("lname") String username, @Query("email") String email, @Query("newpwd") String newpassword);

    @GET("Dashboard")
    Call<Dash_Main_Model> get_user(@Query("stname") String stname, @Query("prjctid") String prjctid, @Query("utype") String utype, @Query("lname") String lname);

    @GET("TreePlantation_Project")
    Call<Project_Model> project(@Query("utype") String username);


    @GET("TreePlantation_AllTreePlanted")
    Call<All_Project_Model> allTree(@Query("utype") String utype, @Query("lname") String lname, @Query("year") String year,
                                    @Query("stateid") String stateid,
                                    @Query("prjctcodeid") String prjctcodeid, @Query("prjctid")
                                            String prjctid, @Query("divid") String divid, @Query("rangeid")
                                            String rangeid, @Query("regionid") String regionid);

    @GET("Treespecies_Master")
    Call<Tree_Species_Model> treeSpicies(@Query("utype") String username);

    @GET("Landtype_Master")
    Call<Land_Type_Model> landType(@Query("utype") String land);

    @GET("PoMO_Master")
    Call<PO_MO_Model> PO_MO(@Query("utype") String land, @Query("lname") String user);

    @GET("Projectcode_Master")
    Call<Project_Code_Model> projectCode(@Query("utype") String land, @Query("lname") String user);


    @GET("Budgethead_Master")
    Call<Budget_Model> budget(@Query("utype") String land, @Query("lname") String user);

    @GET("Yearofmaintence_Master")
    Call<Year_Maintence_Model> year_maintence(@Query("utype") String land, @Query("lname") String user);

    @GET("Dashboard_AddUpdateRecord")
    Call<Details_Responce_Model> details(@Query("stname") String stname, @Query("prjctid") String prjctid, @Query("utype") String utype, @Query("lname") String lname, @Query("rcrd") String rcrd);

    @GET("TreePlantation_ProjectRecord")
    Call<Project_Details_Model> projectDetails(@Query("utype") String utype, @Query("rcrd") String rcrd);



    @GET("SearchAllProject")
    Call<Search_Model>searchProject(@Query("utype") String utype, @Query("lname") String lname, @Query("year") String year, @Query("stateid") String stateid,
                                                      @Query("prjctcodeid") String prjctcodeid, @Query("prjctid") String prjctid, @Query("divid") String divid, @Query("rangeid") String rangeid, @Query("regionid") String regionid);

    @GET("State_Master")
    Call<State_Model> state(@Query("utype") String land, @Query("rid") String rid);


    @GET("Range_Master")
    Call<Range_Model> range(@Query("utype") String utype, @Query("divid") String divid);


    @GET("Region_Master")
    Call<Region_Model> region(@Query("utype") String utype);

    @GET("Division_Master")
    Call<Devision_Name_Model> devision(@Query("utype") String utype, @Query("sid") String sid);

    @GET("TreePlantation_AllTreePlanted_perRecord")
    Call<AllTreePlantDetail> allTreePlantedRecord(@Query("utype") String utype,
                                                  @Query("lname") String lname,
                                                  @Query("year") String year,
                                                  @Query("stateid") String stateid,
                                                  @Query("prjctcodeid") String prjctcodeid,
                                                  @Query("prjctid") String prjctid,
                                                  @Query("divid") String divid,
                                                  @Query("rangeid") String rangeid,
                                                  @Query("regionid") String regionid,
                                                  @Query("rcrdid") String rcrdid);

}
