package it.aton.android.ploapp.utils;

import it.aton.android.ploapp.R;

public class ImagesRepository {
    private Integer pooTypeImages[] = {
            R.drawable.poo_type_1,
            R.drawable.poo_type_2,
            R.drawable.poo_type_3,
            R.drawable.poo_type_4,
            R.drawable.poo_type_5,
            R.drawable.poo_type_6,
            R.drawable.poo_type_7
    };

    private Integer pooColorImages[]={
            R.drawable.poo_color_1,
            R.drawable.poo_color_2,
            R.drawable.poo_color_3,
            R.drawable.poo_color_4,
            R.drawable.poo_color_5,
            R.drawable.poo_color_6
    };

    private final Integer[] pooColors ={
            R.color.poo1,
            R.color.poo2,
            R.color.poo3,
            R.color.poo4,
            R.color.poo5,
            R.color.poo6,
            R.color.normal_poo8,
            R.color.normal_poo9,
            R.color.normal_poo10,
            R.color.poo11,
            R.color.poo12,
            R.color.poo13,
            R.color.poo14,
            R.color.poo15,
            R.color.poo16,
            R.color.poo17,
    };

    public Integer[] getPooTypeImages(){
        return this.pooTypeImages;
    }

    public Integer[] getPooColorImages() {
        return this.pooColorImages;
    }

    public Integer[] getPooColors() {
        return this.pooColors;
    }
}
