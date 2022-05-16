"test REST api"
#uruchomić przy świeżo uruchominej aplikacji

$URI = "http://localhost:8080"
$random_string = -join (('a'..'z') | Get-Random -Count 3)

"================================================="
"get all"
Invoke-RestMethod -Method Get -Uri $URI/products


"================================================="
"get one"

Invoke-RestMethod -Method Get -Uri $URI/product/1

"================================================="
"add one"


$jsonBody = [PSCustomObject]@{
    name="new_name_$random_string"
    price=999
} | ConvertTo-Json

Invoke-RestMethod -Method Post -Uri $URI/product -ContentType 'application/json' -Body $jsonBody

Invoke-RestMethod -Method Get -Uri $URI/products

"================================================="
"delete one"

Invoke-RestMethod -Method Delete -Uri $URI/product/3 

Invoke-RestMethod -Method Get -Uri $URI/products

"================================================="
"update existing one"

$jsonBody = [PSCustomObject]@{
    name="updated existing name $random_string"
    price=999
} | ConvertTo-Json

Invoke-RestMethod -Method Put -Uri $URI/product/2 -ContentType 'application/json' -Body $jsonBody

Invoke-RestMethod -Method Get -Uri $URI/products

"================================================="
"update not existing one"

$jsonBody = [PSCustomObject]@{
    name="updated not existing name $random_string"
    price=999
} | ConvertTo-Json

Invoke-RestMethod -Method Put -Uri $URI/product/99 -ContentType 'application/json' -Body $jsonBody

Invoke-RestMethod -Method Get -Uri $URI/products
