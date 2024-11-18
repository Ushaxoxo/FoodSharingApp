package com.example.android.fud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class LocateNGOFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragement_locate_ngo, container, false);

        ArrayList<NgoInfo> ngoInfo = new ArrayList<NgoInfo>();

        ngoInfo.add(new NgoInfo("Nirmala Foundation",
                "Nirmala Foundation is a team of young people in Delhi who have a desire for social service as well as sympathy towards the miseries of the deprived section of society. As a leading non-profit organization in India, we are working for the combined development of women and children who are vulnerable and often ignored.  \n" +
                        "\n" +
                        "For the past few decades, several non-governmental organizations in India have come into existence, and Nirmala Foundation is one of them.\n" +
                        "\u200B\n" +
                        "We are more than an NGO, we are a strong and passionate family, united in the belief that no one should stand alone.  Many of our staff first came to us as beneficiaries to use our services.  We understand that resilience - the desire to survive - is an extraordinary force.  A sense of belonging and compassion can nurture even the most destitute and fragile people to not just survive, but to thrive in life. We welcome everyone with an open heart and without prejudice.  \n" +
                        "\n" +
                        "A-110/1, Ground FLoor, Shiv Vihar Colony, Karala, New Delhi, Delhi 110081\n.\n" +
                        "7669001136",
                R.drawable.five));


        ngoInfo.add(new NgoInfo("Roti Bank","Mumbai Roti Bank is a non-profit, hunger relief organisation with the mission to eliminate hunger and malnutrition. Started in December 2017 under the able mentorship of former Director General of Police, Maharashtra, Mr. D. Sivanandhan, our initial objective was to reduce the gap between hunger and the excess food. Initially, we used to pick up excess food from weddings, events, hotels, cafeterias, housing societies and deliver it to thousands of hungry people who live in slums or on the footpath. Everyday, we served beneficiaries which primarily include children and also others who are most in need.\n" +
                "DS, B 1803, Ashok towers, Dr. Ambedkar road, Parel , Mumbai 400012\n" +
                "Aspire Tower 2, Flat No.1701, 17th Floor,\n" +
                "Amanora Park Town, Hadapsar, Pune 411028\n" +
                " +91 9892742011 \n" +
                "teamrotibank@gmail.com",
                R.drawable.one));

        ngoInfo.add(new NgoInfo("contact@khushii.org",
                "8.1 million children need help to stay in school. These are the children whom education has passed by. These are the children whom we dream will, one day, all have access to schooling. Are these numbers daunting? Yes, perhaps. Yet, to us, transforming these numbers is what drives us each morning. Established in 2003 by cricket icon Kapil Dev, KHUSHII works tirelessly to keep children in school, through holistic interventions that impact the community. In just 19 years, KHUSHII has transformed the lives of over 1.5 million women and children.\n07947198182\n" +
                        "Khushii NGO, Head Office\n" +
                        "15, A Block Rd, Block A, Okhla Phase II, Okhla Industrial Estate, New Delhi, Delhi 110020 \n",
                R.drawable.two));

        ngoInfo.add(new NgoInfo("From No Food Waste (Chennai Chapter)\n",
                "No Food Waste is a mission to end food waste and hunger to make the “World Hunger Free”. We recover surplus food from weddings, parties and functions and donate it to needy and hungry people. Dedicated Food Recovery Units including Helpline Number, Food Recovery Vehicles, Vessels, Mobile Application and Volunteers are involved for effective process execution.\n"+
                        "+91 9175201229 & +91 9518541719",
                R.drawable.three));

        ngoInfo.add(new NgoInfo("Chennai Foodbank",
                "Chennai Food Bank (CFB) an ISO 9001 : 2015 certified, a project of RYA Madras Metro Trust , formed by Rajasthan Youth Association Metro (RYA METRO), is a not for profit social service organization “Working For A Hunger Free World“ providing Food Grains to more than 60 orphanages, old age homes, homes for handicapped and mentally challenged. We are in the path of service for the past 27 years distributing more than 12000 kg of Food Grains , feeding more than 15000 people and 2000 animals every month. The Food Grains distributed in the last 26 years has crossed well beyond 3 crores meals.\n" +
                        "\n" +
                        "Address: 12, Saravana street, T.Nagar, Chennai - 600017\n" +
                        "Email: info@chennaifoodbank.com\n" +
                        "Phone: +91-8956496198",
                R.drawable.four));


        ngoInfo.add(new NgoInfo("Feed of Love",
                "52, Mgr 3rd Cross St, Narmada Nagar, Nanganallur, Chennai, Tamil Nadu 600061.\n" +
                        "\n" +
                        "8999012209",
                R.drawable.six));

        ngoInfo.add(new NgoInfo("Pephands Foundation",

                        "Pephands Foundation believe in the power of community and the strength of a helping hand. Founded with the vision of making a meaningful difference in the lives of individuals and communities in need, Pephands Foundation is committed to fostering positive change and creating opportunities for growth and development.\n" +
                        "P.no.33, 10, 11th St, Cooperative Nagar, Balaji Nagar Extn-II, Part 2, Adambakkam, Chennai, Tamil Nadu 600088\n" +
                        "7798397966",
                R.drawable.seven));

        ngoInfo.add(new NgoInfo("Lets Feed The Needy\n",
                "The Let’s Feed The Needy is a non-profit organisation was conceptualized and created for the purpose of providing relief and assistance to the poor people at large either in the form of providing meals, clothing, books, uniforms, financial assistance to needy for medical treatment & education purpose, guidance and any other form to uplift the living of the life of the poor.\n" +
                        "\nNo. 4/40 , Sai Vignesh Homes, F2, 1st Floor, 27th Street Thilai Ganga Nagar\n" +
                        "Nanganallur Chennai – 600061 \n" +
                        "07947198061\n"+
                        "lets.feed2014@gmail.com",
                R.drawable.eight));

        ngoInfo.add(new NgoInfo("Pushpa Foundation",
                " It first says an NGO is any non-profit, voluntary citizens' group which is organized on a local, national or international level, but then goes on to restrict the meaning in the sense used by most English speakers and the media: Task-oriented and driven by people with a common interest, NGOs perform a variety of service and humanitarian functions,.\n" +
                        "\n" +
                        "Flat No 204, Kreepa Heritage, Gat No 85 Plot No 71, Kadamwak Wasti, Loni Kalbhor, Pune - 412201, Nr Loni Kalbhor Station\n" +
                        "9860421216",
                R.drawable.nine));




        NgoInfoAdapter adapter = new NgoInfoAdapter(getActivity(),ngoInfo);

        ListView listView = (ListView) root.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return root;
    }

}