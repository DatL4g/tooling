package dev.datlag.tooling.country


data object China : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("CN")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("CHN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(156)
}