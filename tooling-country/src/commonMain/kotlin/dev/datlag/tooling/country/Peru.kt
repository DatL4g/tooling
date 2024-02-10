package dev.datlag.tooling.country


data object Peru : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("PE")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("PER")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(604)
}