package com.markkolenbrander.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import com.markkolenbrander.capstonenewsapp.databinding.ActivityMainBinding
import com.markkolenbrander.capstonenewsapp.models.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val articlesCollection = mutableMapOf<String, MutableList<String?>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        addArticles()
        makingTextViews(articlesCollection)
    }

    private fun addArticles(){
        articlesCollection["author"] = mutableListOf(
            "n-tv NACHRICHTEN-1", "finanzen.net Redaktion", null , "Michael Bassewitz", "Sascha Mattke"
        )
        articlesCollection["title"] = mutableListOf(
            "14:00 Aktienkurs von Softwarekonzern geht durch die Decke - n-tv NACHRICHTEN",
            "US-Inflation hat Höhepunkt erreicht: Jim Cramer sieht das Paradies für Aktien kommen - finanzen.net",
            "Theion: Fragwürdige Wunderakkus aus Berlin - Golem.de - Golem.de",
            "„Weich-Währung“: Experten schlagen wegen Euro-Absturz Alarm - BILD",
            "Tesla Model Y jetzt als Basis-Version für Europa bestellbar, Produktion in China (aktualisiert) - TeslaMag.de"
        )
        articlesCollection["sourceName"] = mutableListOf(
            "ABC News",
            "Ars Technica",
            "Axios",
            "Australian Financial Review",
            "Bloomberg"
        )
    }

    private fun makingTextViews(articles: MutableMap<String, MutableList<String?>>){

        val mainGroup = binding.llMain
        var count = 0
        for (text in mainGroup.children){
            if (text is TextView){

                val content = (
                        if (articles["author"]?.get(count) != null) {
                            articles["author"]?.get(count) + "\n"
                        }else {"...UNKNOWN..." + "\n"} +
                                articles["title"]?.get(count) + "\n" +
                                articles["sourceName"]?.get(count)
                        )
                text.text = content
                count ++
            }
        }
    }

    // I leave this hardcoded List's just in case we have to use them later on
    private val source = arrayListOf(
        Source(
            "abc-news",
            "ABC News",
            "Your trusted source for breaking news, analysis, " +
                    "exclusive interviews, headlines, and videos at ABCNews.com.",
            "https://abcnews.go.com",
            category = Category.GENERAL,
            language = Language.EN,
            country = Country.US,
        ),
        Source(
            "ars-technica",
            "Ars Technica",
            "The PC enthusiast's resource. Power users and the tools they love, " +
                    "without computing religion.",
            "http://arstechnica.com",
            category = Category.TECHNOLOGY,
            language = Language.EN,
            country = Country.US,
        ),
        Source(
            "axios",
            "Axios",
            "Axios are a new media company delivering vital, " +
                    "trustworthy news and analysis in the most efficient, " +
                    "illuminating and shareable ways possible.",
            "https://www.axios.com",
            category = Category.GENERAL,
            language = Language.EN,
            country = Country.US,
        ),
        Source(
            "australian-financial-review",
            "Australian Financial Review",
            "The Australian Financial Review reports the latest news from business, " +
                    "finance, investment and politics, updated in real time. " +
                    "It has a reputation for independent, award-winning journalism and is " +
                    "essential reading for the business and investor community.",
            "http://www.afr.com",
            category = Category.BUSINESS,
            language = Language.EN,
            country = Country.AU
        ),
        Source(
            "bloomberg",
            "Bloomberg",
            "Bloomberg delivers business and markets news, data, analysis, " +
                    "and video to the world, featuring stories from Businessweek and " +
                    "Bloomberg News.",
            "http://www.bloomberg.com",
            category = Category.BUSINESS,
            language = Language.EN,
            country = Country.US,
        )
    )

    private val articles = arrayListOf(
        Article(
            Source(
                id = source[0].id,
                name = source[0].name,
                description = source[0].description,
                url = source[0].url,
                category = source[0].category,
                language = source[0].language,
                country = source[0].country),
            "n-tv NACHRICHTEN-1",
            "14:00 Aktienkurs von Softwarekonzern geht durch die Decke - n-tv NACHRICHTEN",
            "Der Börsen-Tag",
            "https://www.n-tv.de/wirtschaft/der_boersen_tag/Der-Boersen-Tag-Freitag-26-August-2022-article23548649.html",
            "https://bilder4.n-tv.de/img/incoming/crop20342751/4411329297-cImg_16_9-w1200/RTX2ON3M.jpg",
            "2022-08-26T11:26:15Z",
            "Energiekrise und Inflation in Deutschland drücken die Konsumstimmung auf ein Rekordtief. Das Barometer der Nürnberger GfK-Marktforscher signalisiert für September einen überraschend starken Rückgang … [+2007 chars]"
        ),
        Article(
            Source(
                id = source[1].id,
                name = source[1].name,
                description = source[1].description,
                url = source[1].url,
                category = source[1].category,
                language = source[1].language,
                country = source[1].country),
            "finanzen.net Redaktion",
            "US-Inflation hat Höhepunkt erreicht: Jim Cramer sieht das Paradies für Aktien kommen - finanzen.net",
            "In den USA hat sich die Dynamik des Anstiegs der Verbraucherpreise im Juli abgeschwächt. Für Börsenexperte Jim Cramer ist damit eindeutig, dass die Inflation ihren Höhepunkt erreicht hat. Auf viele Aktien sieht er nun goldene Zeiten zukommen.",
            "https://www.finanzen.net/nachricht/aktien/34-peak-inflation-34-us-inflation-hat-hoehepunkt-erreicht-jim-cramer-sieht-das-paradies-fuer-aktien-kommen-11658823",
            "https://images.finanzen.net/mediacenter/unsortiert/inflation_b-calkins_shutterstock_660.jpg",
            "2022-08-26T11:05:00Z",
            "Preisauftrieb in den USA hat im Juli etwas nachgelassenJim Cramer: \\\"Peak Inflation\\\" ist unbestreitbarNirwana für Aktien erreicht\\r\\nDie Dynamik des Preisanstiegs hat in den USA im Juli stärker nachgela… [+2760 chars]"
        ),
        Article(
            Source(
                id = source[2].id,
                name = source[2].name,
                description = source[2].description,
                url = source[2].url,
                category = source[2].category,
                language = source[2].language,
                country = source[2].country),
            null,
            "Theion: Fragwürdige Wunderakkus aus Berlin - Golem.de - Golem.de",
            null,
            "https://www.golem.de/sonstiges/zustimmung/auswahl.html?from=https:%2F%2Fwww.golem.de%2Fnews%2Ftheion-fragwuerdige-wunderakkus-aus-berlin-2208-167435.html",
            null,
            "2022-08-26T10:00:02Z",
            "Besuchen Sie Golem.de wie gewohnt mit Werbung und Tracking, indem Sie der Nutzung aller Cookies zustimmen.\\r\\n Details zum Tracking finden Sie im Privacy Center.\\r\\nSkript wurde nicht geladen. Informatio… [+575 chars]"
        ),
        Article(
            Source(
                id = source[3].id,
                name = source[3].name,
                description = source[3].description,
                url = source[3].url,
                category = source[3].category,
                language = source[3].language,
                country = source[3].country),
            "Michael Bassewitz",
            "„Weich-Währung“: Experten schlagen wegen Euro-Absturz Alarm - BILD",
            "Der Euro ist abgestürzt – und ist jetzt sogar weniger wert als der Dollar. Nur noch 99 US-Cent muss man hinlegen, um einen Euro zu bekommen.",
            "https://www.bild.de/bild-plus/geld/wirtschaft/politik-inland/weich-waehrung-experten-schlagen-wegen-euro-absturz-alarm-81101824.bild.html\"",
            "https://images.bild.de/6305f836ae323113284edaaa/92ccd3b90c3e8651aa722ef88d67cdce,4f9c47fe?w=1280",
            "2022-08-26T09:56:52Z",
            "Euro-Alarm! \\r\\nEuropas Leitwährung ist abgestürzt und war zuletzt zeitweise sogar weniger wert als der Dollar. Nur noch 99 US-Cent musste man am Montag (22. August) hinlegen, um dafür einen Euro zu be… [+480 chars]"
        ),
        Article(
            Source(
                id = source[4].id,
                name = source[4].name,
                description = source[4].description,
                url = source[4].url,
                category = source[4].category,
                language = source[4].language,
                country = source[4].country),
            "Sascha Mattke",
            "Tesla Model Y jetzt als Basis-Version für Europa bestellbar, Produktion in China (aktualisiert) - TeslaMag.de",
            "Die Modell-Politik von Tesla lädt weiter zum Rätseln ein. Nachdem in den USA vor kurzem die Auswahl beim Model 3 auf nur noch zwei Versionen (Basis und",
            "https://teslamag.de/news/tesla-model-y-als-basis-version-neu-in-europa-konfigurator-lieferung-noch-2022-moeglich-52087",
            "https://teslamag.de/wp-content/uploads/tesla-model-y-konfigurator-basis.jpg",
            "2022-08-26T09:11:15Z",
            "Die Modell-Politik von Tesla lädt weiter zum Rätseln ein. Nachdem in den USA vor kurzem die Auswahl beim Model 3 auf nur noch zwei Versionen (Basis und Performance) eingeschränkt wurde, wird sie beim… [+2797 chars]"
        )
    )

}