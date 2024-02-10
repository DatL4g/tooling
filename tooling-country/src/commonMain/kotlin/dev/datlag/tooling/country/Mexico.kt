package dev.datlag.tooling.country


data object Mexico : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("MX")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("MEX")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(484)
}