package com.markkolenbrander.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.children
import com.markkolenbrander.capstonenewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        makingTextViewsWithDefaultValues(source, articles)
    }

//    private fun makingTextViews(){
//        val mainGroup = binding.llMain
//        var count = 0
//        for (text in mainGroup.children){
//            if (text is TextView){
//
//                val content = (
//                        source[count].name + "\n" +
//                                if (articles[count].author != null){
//                                    articles[count].author
//                                }else {"...UNKNOWN..."} + "\n" +
//                                articles[count].title
//                        )
//                text.text = content
//                count ++
//            }
//        }
//    }

    private fun makingTextViewsWithDefaultValues(sourceData: ArrayList<Source>, articleData: ArrayList<Article>){
        val mainGroup = binding.llMain
        var count = 0
        for (text in mainGroup.children){
            if (text is TextView){

                val content = (
                        sourceData[count].name + "\n" +
                                if (articleData[count].author != null){
                                    articleData[count].author
                                }else {"...UNKNOWN..."} + "\n" +
                                articleData[count].title
                        )
                text.text = content
                count ++
            }
        }
    }

//    var articlesMap = mutableMapOf<String, MutableList<Article>>()


    private val source = arrayListOf(
        Source(
            "abc-news",
            "ABC News"
        ),
        Source(
            "ars-technica",
            "Ars Technica"
        ),
        Source(
            "axios",
            "Axios"
        ),
        Source(
            "australian-financial-review",
            "Australian Financial Review"
        ),
        Source(
            null,
            "Bloomberg"
        )
    )

    private val articles = arrayListOf(
        Article(
            "n-tv NACHRICHTEN-1",
            "14:00 Aktienkurs von Softwarekonzern geht durch die Decke - n-tv NACHRICHTEN",
            "Der Börsen-Tag",
            "https://www.n-tv.de/wirtschaft/der_boersen_tag/Der-Boersen-Tag-Freitag-26-August-2022-article23548649.html",
            "https://bilder4.n-tv.de/img/incoming/crop20342751/4411329297-cImg_16_9-w1200/RTX2ON3M.jpg",
            "2022-08-26T11:26:15Z",
            "Energiekrise und Inflation in Deutschland drücken die Konsumstimmung auf ein Rekordtief. Das Barometer der Nürnberger GfK-Marktforscher signalisiert für September einen überraschend starken Rückgang … [+2007 chars]"
        ),
        Article(
            "finanzen.net Redaktion",
            "US-Inflation hat Höhepunkt erreicht: Jim Cramer sieht das Paradies für Aktien kommen - finanzen.net",
            "In den USA hat sich die Dynamik des Anstiegs der Verbraucherpreise im Juli abgeschwächt. Für Börsenexperte Jim Cramer ist damit eindeutig, dass die Inflation ihren Höhepunkt erreicht hat. Auf viele Aktien sieht er nun goldene Zeiten zukommen.",
            "https://www.finanzen.net/nachricht/aktien/34-peak-inflation-34-us-inflation-hat-hoehepunkt-erreicht-jim-cramer-sieht-das-paradies-fuer-aktien-kommen-11658823",
            "https://images.finanzen.net/mediacenter/unsortiert/inflation_b-calkins_shutterstock_660.jpg",
            "2022-08-26T11:05:00Z",
            "Preisauftrieb in den USA hat im Juli etwas nachgelassenJim Cramer: \\\"Peak Inflation\\\" ist unbestreitbarNirwana für Aktien erreicht\\r\\nDie Dynamik des Preisanstiegs hat in den USA im Juli stärker nachgela… [+2760 chars]"
        ),
        Article(
            null,
            "Theion: Fragwürdige Wunderakkus aus Berlin - Golem.de - Golem.de",
            null,
            "https://www.golem.de/sonstiges/zustimmung/auswahl.html?from=https:%2F%2Fwww.golem.de%2Fnews%2Ftheion-fragwuerdige-wunderakkus-aus-berlin-2208-167435.html",
            null,
            "2022-08-26T10:00:02Z",
            "Besuchen Sie Golem.de wie gewohnt mit Werbung und Tracking, indem Sie der Nutzung aller Cookies zustimmen.\\r\\n Details zum Tracking finden Sie im Privacy Center.\\r\\nSkript wurde nicht geladen. Informatio… [+575 chars]"
        ),
        Article(
            "Michael Bassewitz",
            "„Weich-Währung“: Experten schlagen wegen Euro-Absturz Alarm - BILD",
            "Der Euro ist abgestürzt – und ist jetzt sogar weniger wert als der Dollar. Nur noch 99 US-Cent muss man hinlegen, um einen Euro zu bekommen.",
            "https://www.bild.de/bild-plus/geld/wirtschaft/politik-inland/weich-waehrung-experten-schlagen-wegen-euro-absturz-alarm-81101824.bild.html\"",
            "https://images.bild.de/6305f836ae323113284edaaa/92ccd3b90c3e8651aa722ef88d67cdce,4f9c47fe?w=1280",
            "2022-08-26T09:56:52Z",
            "Euro-Alarm! \\r\\nEuropas Leitwährung ist abgestürzt und war zuletzt zeitweise sogar weniger wert als der Dollar. Nur noch 99 US-Cent musste man am Montag (22. August) hinlegen, um dafür einen Euro zu be… [+480 chars]"
        ),
        Article(
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