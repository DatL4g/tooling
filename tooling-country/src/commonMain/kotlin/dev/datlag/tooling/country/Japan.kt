package dev.datlag.tooling.country


data object Japan : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("JP")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("JPN")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(392)
}