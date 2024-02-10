package dev.datlag.tooling.country


data object Syrian : Country {
    override val codeAlpha2: Country.Code.Alpha2 = Country.Code.Alpha2("SY")
    override val codeAlpha3: Country.Code.Alpha3 = Country.Code.Alpha3("SYR")
    override val codeNumeric: Country.Code.Numeric = Country.Code.Numeric(760)
}