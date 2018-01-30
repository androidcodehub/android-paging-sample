package com.androidcodehub.androidpagingsample.model;


import android.os.Parcel;
import android.os.Parcelable;


    public class Product implements Parcelable {
        public String productId;
        public String productName;
        public String shortDescription;
        public String longDescription;
        public String price;
        public String productImage;
        public float reviewRating;
        public int reviewCount;
        public boolean inStock;

        public Product(Parcel in){
            this.productId = in.readString();
            this.productName = in.readString();
            this.shortDescription = in.readString();
            this.longDescription =  in.readString();
            this.price = in.readString();
            this.productImage = in.readString();
            this.reviewRating = in.readFloat();
            this.reviewCount = in.readInt();
            this.inStock = in.readInt() == 1;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(productId);
            dest.writeString(productName);
            dest.writeString(shortDescription);
            dest.writeString(longDescription);
            dest.writeString(price);
            dest.writeString(productImage);
            dest.writeFloat(reviewRating);
            dest.writeInt(reviewCount);
            dest.writeInt(inStock ? 1 : 0);

        }
        @Override
        public String toString() {

            return "id="+productId
                    +",\nimg="+productImage
                    +",\ninstock="+inStock;
        }

        public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {

            @Override
            public Product createFromParcel(Parcel source) {
                return new Product(source);
            }

            @Override
            public Product[] newArray(int size) {
                return new Product[size];
            }
        };
    }

