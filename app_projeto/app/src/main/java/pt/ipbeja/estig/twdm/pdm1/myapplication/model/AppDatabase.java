package pt.ipbeja.estig.twdm.pdm1.myapplication.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Gelado.class, Waffle.class, Crepe.class, Toppings.class, Cart.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GeladoDao getGeladoDao();
    public abstract WaffleDao getWaffleDao();
    public abstract CrepeDao getCrepeDao();
    public abstract ToppingsDao getToppingsDao();
    public abstract CartDao getCartDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "jollyDB").allowMainThreadQueries().addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            db.execSQL("INSERT INTO Gelado VALUES(1, 'Morango','1.00', 'https://i0.wp.com/chocolateamais.com/wp-content/uploads/2018/07/gelado-morango-6.jpg?fit=800%2C533&ssl=1')");
                            db.execSQL("INSERT INTO Gelado VALUES(2, 'Baunilha','1.00', 'https://static.toiimg.com/thumb/63971154.cms?width=573&height=430')");
                            db.execSQL("INSERT INTO Gelado VALUES(3, 'Chocolate','1.00', 'https://www.foodfromportugal.com/content/uploads/2012/11/gelado-chocolate-01.jpg')");
                            db.execSQL("INSERT INTO Gelado VALUES(4, 'Caramelo','1.10', 'https://www.cozinhatradicional.com/wp-content/uploads/2010/07/Molho-de-Caramelo-para-Gelados.jpg')");
                            db.execSQL("INSERT INTO Gelado VALUES(5, 'Menta','1.10', 'https://www.cozinhatradicional.com/wp-content/uploads/2009/08/Gelado-de-Menta-e-Chocolate.jpg')");
                            db.execSQL("INSERT INTO Gelado VALUES(6, 'Snickers','1.35', 'https://blogdagelly.com/wp-content/uploads/2014/12/tips-by-gelly-receita-sorvete-semifreddo-snickers-jamie-oliver-natal010.jpg')");
                            db.execSQL("INSERT INTO Gelado VALUES(7, 'Doce de Ovo','1.10', 'https://2.bp.blogspot.com/-YoZ5k9kJliw/VunShd1eGGI/AAAAAAAAAWA/GUfPwkXEjX0zUNYiJESBw_EVQ3sLNGvyA/s1600/ovo-pascoa.jpg')");
                            db.execSQL("INSERT INTO Gelado VALUES(8, 'Oreo','1.35', 'https://ruralea.com/wp-content/uploads/2021/03/vegan-oreo-ice-cream-flurries-1001205-hero-01-5c92fdbb46e0fb0001376e8f-scaled-e1616102647394.jpg')");
                            db.execSQL("INSERT INTO Gelado VALUES(9, 'Napolitana','1.40', 'https://www.receiteria.com.br/wp-content/uploads/sorvete-napolitano-0.jpg')");
                            db.execSQL("INSERT INTO Gelado VALUES(10, 'Straciatella','1.30', 'https://upload.wikimedia.org/wikipedia/commons/6/6d/Aus_meinem_Gelateria-Labor_-_Stracciatella-Eis_immer_noch_nicht_blutenrein_dafur_ohne_Ei_%284092492038%29.jpg')");


                            db.execSQL("INSERT INTO Waffle VALUES(1, 'Xarope', '2.30', 'https://cdn.shopify.com/s/files/1/0148/1945/9126/articles/Maple_Waffles_89a00241-086e-4724-918a-521f852e00ad_720x.jpg?v=1586286648')");
                            db.execSQL("INSERT INTO Waffle VALUES(2, 'Nutella', '2.35', 'https://w7.pngwing.com/pngs/90/104/png-transparent-belgian-waffle-treacle-tart-danish-pastry-belgian-cuisine-chocolate-food-breakfast-frozen-dessert.png')");
                            db.execSQL("INSERT INTO Waffle VALUES(3, 'Caramelo', '2.35', 'https://live.staticflickr.com/3205/2620162224_d12b4c9db3_b.jpg')");
                            db.execSQL("INSERT INTO Waffle VALUES(4, 'Doce de Morango', '2.35', 'https://meumundinhodesofia.files.wordpress.com/2015/01/dsc09608.jpg')");
                            db.execSQL("INSERT INTO Waffle VALUES(5, 'Xarope de Framboesa', '2.35', 'https://static.sscontent.com/thumb/500/500/products/124/v1125066_prozis_zero-raspberry-syrup-355-g_6.jpg')");
                            db.execSQL("INSERT INTO Waffle VALUES(6, 'Mel', '2.10', 'https://uploads.metropoles.com/wp-content/uploads/2020/10/07132859/waffle-4.jpg')");


                            db.execSQL("INSERT INTO Crepe VALUES(1, 'Nutella', '2.35', 'https://img.cybercook.com.br/receitas/388/crepe-de-nutella.jpeg')");
                            db.execSQL("INSERT INTO Crepe VALUES(2, 'Caramelo', '2.30', 'https://www.seriouseats.com/thmb/78ALi_58dqINjYH1BhdSWxbUSvI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/__opt__aboutcom__coeus__resources__content_migration__serious_eats__seriouseats.com__recipes__images__2012__11__20121106-wakeandbake-crepes-with-caramel-sauce-0a5d5a00b50e4fedbe7a05afcab1bf38.JPG')");
                            db.execSQL("INSERT INTO Crepe VALUES(3, 'Doce de Morango', '2.35', 'https://cdn.panelinha.com.br/receita/991191600000-Crepe-de-morango.jpg')");
                            db.execSQL("INSERT INTO Crepe VALUES(4, 'Doce de Leite', '2.30', 'https://claudia.abril.com.br/wp-content/uploads/2020/03/panqueque-dulce-leche-04.jpg')");
                            db.execSQL("INSERT INTO Crepe VALUES(5, 'Kinder Bueno', '2.40', 'https://emilygracefood.com/wp-content/uploads/2020/10/IMGL0029-2.jpg')");
                            db.execSQL("INSERT INTO Crepe VALUES(6, 'Doce de Abóbora', '2.35', 'https://s2-receitas.glbimg.com/qCoFIZCuGBQ-MMbtKmQakQgxxT4=/0x0:500x300/984x0/smart/filters:strip_icc()/s.glbimg.com/po/rc/media/2012/09/14/14_08_27_457_panqueca_de_doce_de_leite1.jpg')");

                            db.execSQL("INSERT INTO Toppings VALUES(1, 'Açucar', '0.35€', 'https://www.iguaria.com/wp-content/uploads/2021/08/Iguaria-Waffle-com-Canela-e-Acucar.jpg')");
                            db.execSQL("INSERT INTO Toppings VALUES(2, 'Barquillo', '0.50€', 'https://singlutum.es/wp-content/uploads/2021/07/receta-de-barquillos.jpg')");
                            db.execSQL("INSERT INTO Toppings VALUES(3, 'Canela', '0.35€', 'https://static.tuasaude.com/media/article/ns/sc/beneficios-da-canela_18873_l.jpg')");
                            db.execSQL("INSERT INTO Toppings VALUES(4, 'Morango', '0.50', 'https://cdn.shopify.com/s/files/1/0541/5842/6305/products/morango_grande.jpg?v=1614794190')");
                            db.execSQL("INSERT INTO Toppings VALUES(5, 'Banana', '0.50', 'https://saboreiaavida.nestle.pt/sites/default/files/styles/article_header_mobile_575x310/public/pictures/857e1cab-ac66-11e4-9488-d4ae52be23e7.png?itok=TlOQXsuc')");
                            db.execSQL("INSERT INTO Toppings VALUES(6, 'Amendoim', '0.45', 'https://minhasaude.proteste.org.br/wp-content/uploads/2022/08/amendoim.jpg')");
                            db.execSQL("INSERT INTO Toppings VALUES(7, 'Chantilly', '0.30', 'https://theviewfromgreatisland.com/wp-content/uploads/2023/06/Chantilly-Cream-5556-June-15-2023-2.jpg')");
                            db.execSQL("INSERT INTO Toppings VALUES(8, 'M&Ms', '0.35', 'https://nuts.com/images/rackcdn/ed910ae2d60f0d25bcb8-80550f96b5feb12604f4f720bfefb46d.ssl.cf1.rackcdn.com/94d58267f4ba20ad-yJfcdaDY.jpg')");
                            db.execSQL("INSERT INTO Toppings VALUES(9, 'Twix', '0.50', 'https://seppishop.com/media/image/product/2597/md/twix-50gr-x-25_1.jpg')");

                        }
                    })
                    .build();
        }
        return INSTANCE;
    }
}
