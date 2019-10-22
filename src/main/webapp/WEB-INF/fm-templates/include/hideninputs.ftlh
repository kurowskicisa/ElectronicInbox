
<input
        id="inputName"
        name="nazwa"
        type="hidden"
        value="<#if choiceName_??>${choiceName_}<#else > </#if>"
>

<input
        id="inputAddress"
        name="adres"
        type="hidden"
        value="<#if choiceAddress_??>${choiceAddress_}<#else > </#if>"
>

<input
        id="inputPlace"
        name="miejscowosc"
        type="hidden"
        value="<#if choicePlace_??>${choicePlace_}<#else > </#if>"
>

<input
        id="inputPage"
        name="strona"
        type="hidden"
        value=<#if choicePage_??>${choicePage_}<#else >1</#if>
>
