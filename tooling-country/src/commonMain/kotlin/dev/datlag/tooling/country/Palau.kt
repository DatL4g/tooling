package dev.datlag.tooling.country


data object Palau : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("PW")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("PLW")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(585)
}