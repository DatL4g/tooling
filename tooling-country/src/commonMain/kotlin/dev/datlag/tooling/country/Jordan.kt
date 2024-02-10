package dev.datlag.tooling.country


data object Jordan : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("JO")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("JOR")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(400)
}