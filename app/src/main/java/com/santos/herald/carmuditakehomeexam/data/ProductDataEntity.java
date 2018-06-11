package com.santos.herald.carmuditakehomeexam.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductDataEntity extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("fk_country")
    @Expose
    public String fkCountry;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("attribute_set_name")
    @Expose
    public String attributeSetName;
    @SerializedName("attribute_set_name_local")
    @Expose
    public String attributeSetNameLocal;
    @SerializedName("approved")
    @Expose
    public String approved;
    @SerializedName("status_supplier_config")
    @Expose
    public String statusSupplierConfig;
    @SerializedName("activated_at")
    @Expose
    public String activatedAt;
    @SerializedName("listing_start")
    @Expose
    public String listingStart;
    @SerializedName("listing_end")
    @Expose
    public String listingEnd;
    @SerializedName("fk_vertical")
    @Expose
    public String fkVertical;
    @SerializedName("fk_catalog_brand")
    @Expose
    public String fkCatalogBrand;
    @SerializedName("fk_catalog_brand_model")
    @Expose
    public String fkCatalogBrandModel;
    @SerializedName("brand_model_edition")
    @Expose
    public String brandModelEdition;
    @SerializedName("listing_type")
    @Expose
    public String listingType;
    @SerializedName("listing_country")
    @Expose
    public String listingCountry;
    @SerializedName("listing_area")
    @Expose
    public String listingArea;
    @SerializedName("condition")
    @Expose
    public String condition;
    @SerializedName("condition_position")
    @Expose
    public String conditionPosition;
    @SerializedName("condition_id")
    @Expose
    public String conditionId;
    @SerializedName("color_family_position")
    @Expose
    public String colorFamilyPosition;
    @SerializedName("color_family_id")
    @Expose
    public String colorFamilyId;
    @SerializedName("doors_position")
    @Expose
    public String doorsPosition;
    @SerializedName("doors_id")
    @Expose
    public String doorsId;
    @SerializedName("power")
    @Expose
    public String power;
    @SerializedName("drive_type_position")
    @Expose
    public String driveTypePosition;
    @SerializedName("drive_type_id")
    @Expose
    public Integer driveTypeId;
    @SerializedName("interior")
    @Expose
    public String interior;
    @SerializedName("exterior")
    @Expose
    public String exterior;
    @SerializedName("equipment")
    @Expose
    public String equipment;
    @SerializedName("warranty_type_position")
    @Expose
    public String warrantyTypePosition;
    @SerializedName("warranty_type_id")
    @Expose
    public String warrantyTypeId;
    @SerializedName("warranty_years_position")
    @Expose
    public String warrantyYearsPosition;
    @SerializedName("warranty_years_id")
    @Expose
    public String warrantyYearsId;
    @SerializedName("price_conditions_position")
    @Expose
    public String priceConditionsPosition;
    @SerializedName("price_conditions_id")
    @Expose
    public String priceConditionsId;
    @SerializedName("premium_listing")
    @Expose
    public String premiumListing;
    @SerializedName("alternate_sku")
    @Expose
    public String alternateSku;
    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("root_category")
    @Expose
    public String rootCategory;
    @SerializedName("agency_logo")
    @Expose
    public String agencyLogo;
    @SerializedName("new-product")
    @Expose
    public Boolean newProduct;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("location_latitude")
    @Expose
    public String locationLatitude;
    @SerializedName("location_longitude")
    @Expose
    public String locationLongitude;
    @SerializedName("google_formatted_address")
    @Expose
    public String googleFormattedAddress;
    @SerializedName("google_place_id")
    @Expose
    public String googlePlaceId;
    @SerializedName("fk_customer_address")
    @Expose
    public String fkCustomerAddress;
    @SerializedName("listing_region")
    @Expose
    public String listingRegion;
    @SerializedName("listing_city")
    @Expose
    public String listingCity;
    @SerializedName("agency_address")
    @Expose
    public String agencyAddress;
    @SerializedName("agency_city")
    @Expose
    public String agencyCity;
    @SerializedName("fk_country_region")
    @Expose
    public String fkCountryRegion;
    @SerializedName("fk_country_region_city")
    @Expose
    public String fkCountryRegionCity;
    @SerializedName("fk_country_region_city_area")
    @Expose
    public String fkCountryRegionCityArea;
    @SerializedName("show_listing_address")
    @Expose
    public String showListingAddress;
    @SerializedName("item_contact_name")
    @Expose
    public String itemContactName;
    @SerializedName("item_contact_email")
    @Expose
    public String itemContactEmail;
    @SerializedName("item_contact_mobile")
    @Expose
    public String itemContactMobile;
    @SerializedName("item_contact_homephone")
    @Expose
    public String itemContactHomephone;
    @SerializedName("agency_name")
    @Expose
    public String agencyName;
    @SerializedName("product_owner_url_key")
    @Expose
    public String productOwnerUrlKey;
    @SerializedName("product_owner")
    @Expose
    public String productOwner;
    @SerializedName("fk_customer")
    @Expose
    public String fkCustomer;
    @SerializedName("is_agent")
    @Expose
    public String isAgent;
    @SerializedName("seller_is_trusted")
    @Expose
    public String sellerIsTrusted;
    @SerializedName("show_officephone")
    @Expose
    public String showOfficephone;
    @SerializedName("show_homephone")
    @Expose
    public String showHomephone;
    @SerializedName("show_mobile")
    @Expose
    public String showMobile;
    @SerializedName("sku")
    @Expose
    public String sku;
    @SerializedName("id_catalog_config")
    @Expose
    public String idCatalogConfig;
    @SerializedName("attribute_set_id")
    @Expose
    public String attributeSetId;
    @SerializedName("original_price_currency")
    @Expose
    public String originalPriceCurrency;
    @SerializedName("is_certified")
    @Expose
    public Integer isCertified;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("urlkey_details")
    @Expose
    public String urlkeyDetails;
    @SerializedName("price_not_available")
    @Expose
    public String priceNotAvailable;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("original_price")
    @Expose
    public String originalPrice;
    @SerializedName("brand")
    @Expose
    public String brand;
    @SerializedName("brand_model")
    @Expose
    public String brandModel;
    @SerializedName("top_position")
    @Expose
    public String topPosition;
    @SerializedName("mileage_not_available")
    @Expose
    public String mileageNotAvailable;
    @SerializedName("mileage")
    @Expose
    public String mileage;
    @SerializedName("config_id")
    @Expose
    public String configId;
    @SerializedName("rejected_comment")
    @Expose
    public String rejectedComment;
    @SerializedName("transmission")
    @Expose
    public String transmission;
    @SerializedName("transmission_position")
    @Expose
    public String transmissionPosition;
    @SerializedName("transmission_id")
    @Expose
    public String transmissionId;
    @SerializedName("fuel")
    @Expose
    public String fuel;
    @SerializedName("fuel_position")
    @Expose
    public String fuelPosition;
    @SerializedName("fuel_id")
    @Expose
    public String fuelId;

    public String getThumbnailFirstImage(Realm realm){
        return realm.where(ImagesEntity.class).equalTo("productId", id).findAll().get(0).url;
    }

}
