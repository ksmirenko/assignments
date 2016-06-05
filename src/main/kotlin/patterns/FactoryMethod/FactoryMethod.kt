@file:Suppress("unused")

package patterns.FactoryMethod

import java.util.*

abstract class Page { }

class SkillsRage       : Page() { }

class EducationPage    : Page() { }

class ExperiencePage   : Page() { }

class IntroductionPage : Page() { }

class ResultsPage      : Page() { }

class ConclusionPage   : Page() { }

class SummaryPage      : Page() { }

class BibliographyPage : Page() { }

abstract class Document
{
    private var pages = LinkedList<Page>()

    var Pages: LinkedList<Page> = pages
        get() = field

    fun Document() {
        CreatePages()
    }
    // Factory Method
    abstract fun CreatePages()
}
class Resume : Document()
{

    // Factory Method implementation
    override fun CreatePages()
    {
        Pages.add(SkillsRage())
        Pages.add(EducationPage())
        Pages.add(ExperiencePage())
    }
}

class Report : Document()
{
    // Factory Method implementation
    override fun CreatePages()
    {
        Pages.add(IntroductionPage())
        Pages.add(ResultsPage())
        Pages.add(ConclusionPage())
        Pages.add(SummaryPage())
        Pages.add(BibliographyPage())
    }
}

