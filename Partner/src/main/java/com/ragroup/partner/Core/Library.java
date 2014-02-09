package com.ragroup.partner.Core;

import com.ragroup.Models.Partner;
import com.ragroup.Models.Product;
import com.ragroup.partner.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by kiolt_000 on 04.02.14.
 */
public class Library {

    public static HashMap<String,Partner> partners;
    public static HashMap<String,Product> products;


    public static Partner me;

    public static void initialize() {
        final Product product = new Product(){{
            id = "ra12";
            name = "Allure Homme Sport";
            description = "Этот аромат именно то, что нужно молодому, энергичному мужчине, предпочитающему спортивный стиль. Его обладатель привык побеждать и не идет на компромиссы. Аромат смело можно назвать идеальным, сексуальным и спортивным одновременно";
            drawableId = R.drawable.allure_homme_sport;
            price = 100;
        }};
        final Product product1 = new Product(){{
            id = "ra13";
            name = "Allure Homme Sport2";
            description = "Этот аромат именно то, что нужно молодому, энергичному мужчине, предпочитающему спортивный стиль. Его обладатель привык побеждать и не идет на компромиссы. Аромат смело можно назвать идеальным, сексуальным и спортивным одновременно";

            character = "темпераментный, женственный.";
            family = "восточные";
            base = "мускус, цитрусовые.";
            mid = "мускус, цитрусовые.";
            top = "мускус, цитрусовые.";

            drawableId = R.drawable.allure_homme_sport;
            price = 100;
            priceVip = 150;
            priceClient = 200;
        }};


        final Partner partner0 = new Partner(){{
            id = "US123124";
            name = "Chris Hughes";
        }};
        final Partner partner1 = new Partner(){{
            id = "US123125";
            name = "Dustin Moskovitz";
        }};
        final Partner partner = new Partner(){{
            id = "US123123";
            name = "Mark Zuckerberg";
            commits.put("2014-10-31",
                    new Commit(){{
                        pv = 32.5;
                        gv = 32.5;
                        pgv = 32.5;
                        sgv = 32.5;
                        status = "Director";
                        bonus = 0;
                        tree = new ArrayList<Partner>(){{
                            add(partner0);
                            add(partner1);
                            add(partner0);
                            add(partner1);
                            add(partner0);
                            add(partner1);
                        }};
                    }}
            );
        }};
        me = partner;

        partners = new HashMap<String, Partner>(){{
            put(partner.id,partner);
            put(partner0.id,partner0);
            put(partner1.id,partner1);
        }};

        products = new HashMap<String, Product>(){{
            put(product.id, product);
            put(product1.id, product1);
        }};
    }
}
