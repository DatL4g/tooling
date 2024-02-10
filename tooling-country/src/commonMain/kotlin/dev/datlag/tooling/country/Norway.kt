package dev.datlag.tooling.country


data object Norway : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("NO")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("NOR")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(578)
}