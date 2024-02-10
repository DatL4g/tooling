package dev.datlag.tooling.country


data object Israel : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("IL")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("ISR")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(376)
}