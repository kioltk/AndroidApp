package com.ragroup.partner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ragroup.Adapters.InfoAdapter;
import com.ragroup.Adapters.ProductsAdapter;
import com.ragroup.Adapters.PromotionsAdapter;
import com.ragroup.Models.Info;
import com.ragroup.Models.Partner;
import com.ragroup.Models.Product;
import com.ragroup.Models.Promotion;
import com.ragroup.partner.Core.Library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    //todo: Навигация хватется с центра

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Library.initialize();

        setContentView(R.layout.activity_main);

        final Context context = this;

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                drawer
                );

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        mMenu = R.menu.main;
        //todo: впихиваем по номеру фрагмент



        switch (position){

            //todo: достаём нашего юзера
            case 0:{
                //R.string.navigation_section_account
                mMenu = R.menu.user;
                Partner partner = Library.me;
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new UserActivity.PlaceholderFragment(this, partner))
                        .commit();


            }break;
            case 1:{
                //R.string.navigation_section_pricing
                ArrayList<Product> list = new ArrayList<Product>(Library.products.values());
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new ProductsFragment(this,list))
                        .commit();
            }
            break;
            //R.string.navigation_section_calculator
            case 3:{
                //R.string.navigation_section_marketing
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MarketingFragment(this))
                        .commit();
            }break;
            case 4:{
                //R.string.navigation_section_culture
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new CultureFragment(this))
                        .commit();
            }
            break;
            case 5:{
                //R.string.navigation_section_promotions
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new PromotionsFragment(this))
                        .commit();
            }break;
            case 6:{
                //R.string.navigation_section_info
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new MaterialsFragment(this))
                        .commit();
            }break;

        }
    }




    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    int mMenu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            getMenuInflater().inflate(mMenu, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_example:

                Toast.makeText(this, "Pick date.", Toast.LENGTH_SHORT).show();//todo:выбор даты
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }


    }

    public static class ProductsFragment extends Fragment{

        Context context;
        ArrayList<Product> list;
        public ProductsFragment(Context context, ArrayList<Product> list) {

            this.context = context;
            this.list = list;

        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_products, container, false);

            ListView listView = (ListView) rootView.findViewById(R.id.list);

            listView.setAdapter(new ProductsAdapter(context,list));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Product product = (Product) parent.getItemAtPosition(position);

                    Intent intent = new Intent(context,ProductActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", product.id );
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });

            return rootView;

        }
    }

    public static class CultureFragment extends Fragment {
        Context context;
        public CultureFragment(Context context) {
            this.context = context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);




            ListView listView = (ListView) rootView.findViewById(R.id.list);
            final Info item = new Info(){{
                header = "МИССИЯ КОМПАНИИ БАЗИРУЕТСЯ НА ТРЕХ КЛЮЧЕВЫХ ПРИНЦИПАХ:";
                body = "-Развитие бизнеса - ДИНАМИЧНОЕ.\n" +
                        "-Качество продуктов и услуг - ЭКСКЛЮЗИВНОЕ.\n" +
                        "-Профессионализм команды – ВЫСОКИЙ.";

            }};
            final Info item0 = new Info(){{
                header = "СОДЕРЖАНИЕ МИССИИ КОМПАНИИ RA GROUP Int.:";
                body = "Мы дарим гармонию красоты и возможность реализовать самые амбициозные Цели!";
            }};
            final Info item1 = new Info(){{
                header = "RA GROUP Int. ИДЕТ К ЛИДЕРСТВУ, ПРЕДОСТАВЛЯЯ:";
                body = "-Клиентам – оптимальное соотношение цены и качества эксклюзивной парфюмерии и косметики, а также элитный сервис консультационных услуг.\n" +
                        "«RA GROUP Int. - гарантия качества!\n" +
                        "\n" +
                        "-Бизнес Партнерам - уникальную возможность реализовать поставленные «сильные» личные цели и Мечты, а также простую, легко дуплицированную систему ведения бизнеса.\n" +
                        "«Ra GROUP Int. – это способ реализовать Жизнь Вашей Мечты».\n" +
                        "\n" +
                        "-Деловым Партнерам – широкую, надежную, быстро растущую дистрибуторскую сеть простую, легко дуплицированную  сеть по продвижению товаров и высокие стандарты этики бизнеса.     \n" +
                        "«RA GROUP Int.» - престиж вашего имиджа!».";
            }};
            final Info item2 = new Info(){{
                header = "СТРАТЕГИЯ КОМПАНИИ RA GROUP Int.:";
                body = "Шарм натуральной косметики и парфюмерии  высокой моды в каждый дом!";
            }};
            final Info item3 = new Info(){{
                header = "СТРАТЕГИЯ КОМПАНИИ ОПИРАЕТСЯ НА ТРИ КЛЮЧЕВЫХ ПРИНЦИПА:";
                body = "-высокий профессионализм Бизнес Партнеров  компании;\n" +
                        "-взаимное уважение и стабильность деловых отношений;\n" +
                        "-максимальная выгода для клиентов компании.";
            }};
            final Info item4 = new Info(){{
                header = "СТРАТЕГИЯ КОМПАНИИ ОРИЕНТИРОВАНА НА ДОСТИЖЕНИЕ ТРЕХ ВЗАИМОСВЯЗАННЫХ ЦЕЛЕЙ:";
                body = "-Создание совершенной системы обслуживания клиентов.\n" +
                        "\n" +
                        "Мы предлагаем клиентам 5 возможностей, удовлетворяющих ваши потребности - эксклюзивный ассортимент, цены от производителей, международные стандарты гарантии качества продукции и комфортное сервисное обслуживание.\n" +
                        "\n" +
                        " -Формирование сильной самонастраивающейся корпоративной культуры.\n" +
                        "\n" +
                        "Бизнес Партнеры компании Ra GROUP понимают, что высокая общая культура = доверие клиентов = прибыли = успеху каждого и всей команды.\n" +
                        "\n" +
                        " -Профессиональные стандарты работы Бизнес Партнеров.\n" +
                        "\n" +
                        "Признание и доверие клиентов достигается путем выполнения Бизнес Партнерами функций маркетологов (поиск потенциальных покупателей), пиар-менеджеров (формирование доверия у покупателей) и профессиональных переговорщиков (перевод покупателей в разряд постоянных клиентов).";
            }};
            final Info item5 = new Info(){{
                header = "Достижение поставленных целей позволит с максимальным эффектом реализовать основные конкурентные преимущества компании:";
                body = "-оперативное удовлетворение постоянно меняющихся высоких стандартов потребительских ожиданий;\n" +
                        "-высокую репутацию компании для создания деловых альянсов, обеспечивающих лидирующие позиции на основных сегментах рынка;\n" +
                        "-высокопрофессиональную работу Бизнес-партнеров, продвигающих на рынок постоянно расширяющийся ассортимент продукции;\n" +
                        "-развитые региональные структуры сетевого маркетинга.";
            }};

            ArrayList<Info> list = new ArrayList<Info>(){{

                add(item);
                add(item0);
                add(item1);
                add(item2);
                add(item3);
                add(item4);
                add(item5);

            }};


            listView.setAdapter(new InfoAdapter(context,list));

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }


    }

    public static class MarketingFragment extends Fragment {
        Context context;
        public MarketingFragment(Context context) {
            this.context = context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);




            ListView listView = (ListView) rootView.findViewById(R.id.list);

            final Info item = new Info(){{
                header = "Сотрудничая с компанией RA GROUP Int., Вы получаете следующие уникальные возможности:";

            }};
            final Info item0 = new Info(){{
                body = "1.Приобретение продукции по специальным оптовым ценам.\n" +
                        "2.Прибыль от розничных продаж (от 50 % чистой прибыли).\n" +
                        "3.Бонус за групповой объем  – процент от товарооборота в баллах Вашей организации (от 5% до 58,5%).\n" +
                        "4.Премии за закрытие и за повторное закрытие статусов (от 100 до 1000 евро).\n" +
                        "5.Дополнительные премии, подарки, промоушены от руководства  компании RA GROUP Int.\n" +
                        "6.Премии Президентского фонда компании RA GROUP Int.";
            }};
            final Info item1 = new Info(){{
                header = "Маркетинговый план (МП)";
            }};
            final Info item2 = new Info(){{
                header = "Преимущества";
                body = "- МП компании RA GROUP Int. является накопительным и несгораемым.\n" +
                        "- Статусы закрываются один раз и закрепляются за Бизнес Партнером независимо от того, какой групповой объем (ГО) делает его организация в последующий отчетный период.\n" +
                        "- Постоянно сохраняется процент бонусных выплат, соответствующий закрытому статусу.\n" +
                        "- Одни из самых высоких  бонусных выплат.\n" +
                        "- Доступность минимального личного объема (ЛО) для получения бонусных выплат.\n" +
                        "- МП сочетает выгодные условия для получения бонусных выплат для всех категорий и статусов Бизнес Партнеров.";

            }};
            final Info item3 = new Info(){{
                header = "Понятия и термины:";
                body = "Бизнес Партнер  (БП) – физическое или юридическое лицо, подписавшее “Заявление” о сотрудничестве с компанией с присвоением индивидуального регистрационного номера.\n" +
                        "Балл  (Б) – универсальная единица ценности товара.\n" +
                        "Активный Бизнес Партнер – БП, выполнивший ЛО в отчетный период - 20 Б и более.\n" +
                        "Личный объем (ЛО) – сумма баллов, сделанных  Бизнес Партнером лично. \n" +
                        "Отчетный период – календарный месяц.\n" +
                        "Личная группа (ЛГ) -  группа  БП, в которую входят все его  БП, за исключением БП от ранга Директор и их  организаций.\n" +
                        "Организация (Группа) – все БП, подписавшее “Заявление” о сотрудничестве с компанией и приглашенные БП лично и БП его нижних  уровней, включая самого БП.\n" +
                        "Групповой объем (ГО) – сумма баллов, сделанных  БП лично и его организацией за  отчетный период.\n" +
                        "Объем личной группы (ОЛГ) - сумма баллов, сделанных  БП лично и его организацией за  отчетный период, за исключением БП от  ранга Директор и их  организаций.\n" +
                        "Накопительный групповой объем (НГО) – сумма баллов, сделанных лично БП  и его организацией за весь период сотрудничества с компанией.\n" +
                        "Информационный спонсор  - БП, привлекший другого БП.\n" +
                        "Статус (Ранг) – показатель, отражающий успехи БП в  построение своей дистрибьюторской сети.\n" +
                        "Статусы - Стажер, Консультант, Менеджер 3 уровня, Менеджер 2 уровня, Менеджер 1 уровня, Директор, Серебряный Директор, Золотой Директор, Рубиновый Директор, Бриллиантовый Директор, Советник Президента, Дважды Советник Президента.\n" +
                        "Поколение (Уровень) – БП, для которого Вы являетесь информационным спонсором, считается Вашим 1-ым поколением. БП 2-го поколения считается БП, чьим информационным спонсором является Ваш Бизнес Партнер 1-го поколения и т.д.\n" +
                        "Бонус – начисляется активному БП за ГО, выполненный за отчетный период, в соответствии с МП. \n" +
                        "Квалифицированный Директор – БП в статусе от Директора и выше, ОЛГ которого за отчетный период:\n" +
                        "- для статусов Директор, Серебряный  Директор, Золотой Директор и Рубиновый  Директор 700 Б и более. \n" +
                        "- для статуса Бриллиантовый Директор 600 Б и более.\n" +
                        "- для статуса Советник  Президента 500 Б и более.\n" +
                        "VIP-клиент – физическое или юридическое лицо, подписавшее “Заявление” потребителя и получивший право приобретения продукции по складским ценам без начисления бонуса.\n" +
                        "Складская цена – оптовая цена продукции, по которой ее приобретают все БП и VIP-клиенты компании.";
            }};
            ArrayList<Info> list = new ArrayList<Info>(){{
                add(item);
                add(item0);
                add(item1);
                add(item2);
                add(item3);

            }};

            listView.setAdapter(new InfoAdapter(context,list));

            return rootView;
        }


    }

    public static class PromotionsFragment extends Fragment {
        Context context;
        public PromotionsFragment(Context context) {
            this.context = context;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);




            ListView listView = (ListView) rootView.findViewById(R.id.list);

            final Promotion item = new Promotion(){{
                header = "«Моя мечта – АВТОМОБИЛЬ»";
                image = R.drawable.promotion1;
                content = new ArrayList<Object>(){{
                    add(R.drawable.promotion2);
                    add("Время проведения промоушена: январь-декабрь 2014 года\n" +
                            " \n" +
                            "- ГО за период проведения промоушена согласно таблицы 1:");
                    add(R.drawable.promotion3);
                    add("- *Суммарный прирост ГО в сравнении с аналогичным периодом январь-декабрь 2013 года – не менее 10%\n" +
                            "- Месячный ГО в период выполнения промоушена не менее 3000 баллов. Если месячный ГО бизнес-партнера в период выполнения промоушена в любом из месяцев составит менее 3000 баллов, то набранные баллы в этом периоде не учитываются в суммарный ГО промоушена\n" +
                            "- Выполнение правила 30/70 – ГО самой большой ветки не должен превышать более 70% от общего ГО бизнес-партнера\n" +
                            "- Если нижестоящий БП выполнил условия промоушена, то для выполнения условий промоушена для вышестоящего БП, его ГО помимо «веток-победителей» должен составить не менее того количества баллов, согласно которых он может претендовать на одну из моделей автомобилей представленных в таблице 1\n" +
                            " \n" +
                            "Примечание:\n" +
                            "* Данное условие только для БП подписанных до 01.02.2013 года, для подписанных после 01.02.2013 прирост ГО не требуется\n" +
                            "Промоушен не выплачивается в денежном эквиваленте");
                }};
            }};
            ArrayList<Promotion> list = new ArrayList<Promotion>(){{
                add(item);
                add(item);
            }};
            listView.setPadding(0,0,0,0);
            listView.setAdapter(new PromotionsAdapter(context,list));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Promotion promotion = (Promotion) parent.getItemAtPosition(position);

                    Intent intent = new Intent(context, PromotionActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", promotion.id );
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });


            return rootView;
        }


    }

    public static class MaterialsFragment extends Fragment{
        private final Context context;

        public MaterialsFragment(Context context){

            this.context = context;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_info,container,false);



            ListView listView = (ListView) rootView.findViewById(R.id.list);

            Info material = new Info(){{
                id = "1";
                header = "Тема 1";
                body = "Подписание контракта";
            }};

            Info material1 = new Info(){{
                header = "Тема 2";
                body = "Чего хочет клиент";
            }};
            Info material2 = new Info(){{
                body = "Пособие по продажам";
            }};
            Info material3 = new Info(){{
                body = "Работа с выражениями";
            }};
            Info material4 = new Info(){{
                header = "Аудио подкаст";
                body = "Телефонные звонки";
            }};
            Info material5 = new Info(){{
                header = "Видео";
                body = "Список знакомых";
            }};

            ArrayList<Info> list = new ArrayList<Info>();

            list.add(material);
            list.add(material1);
            list.add(material2);
            list.add(material3);
            list.add(material4);
            list.add(material5);


            listView.setAdapter(new InfoAdapter(context, list));

            listView.setSelector(R.drawable.choise_item);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Info material = (Info) parent.getItemAtPosition(position);

                    String path = "";
                    String filePath = material.id + ".docx";
                    String fileName = material.body + ".docx";

                    try {

                        InputStream file = context.getAssets().open(filePath);

                        path =  context.getExternalCacheDir().getAbsolutePath();

                        OutputStream outputStream = new FileOutputStream(new File(path, fileName));

                        int read = 0;
                        byte[] bytes = new byte[1024];

                        while ((read = file.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }
                        filePath = path + "/"+ fileName;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Uri uri = Uri.fromFile(new File(filePath));
                    try {

                        Intent objIntent = new Intent(Intent.ACTION_VIEW);
                        objIntent.setDataAndType(uri,"application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                        objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(objIntent);

                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(context,
                                getResources().getString(R.string.doc_viewer_error), Toast.LENGTH_SHORT)
                                .show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    /*

                    Intent intent = new Intent(context, MaterialActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", material.id );
                    intent.putExtras(bundle);
                    startActivity(intent);
                    */

                }
            });

            return rootView;

        }
    }
}
