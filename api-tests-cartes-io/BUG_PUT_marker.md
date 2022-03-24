# BUG:PUT request for markers endpoint returns 403 Unauthorized

## Steps to reproduce:

1. Create a public map:

* Request:

```bash
  curl -v -X POST 'https://cartes.io/api/maps/' -H 'Accept: application/json, application/javascript, text/javascript, text/json' -H 'Content-Type: application/json' -d '{ "title": "wzLCVsqaKigXNVA", "description": "mdCLuzFqxRRKDefWAEtaJoeXLoAdVlEeFXsOfueTSSSBrerDMqBM", "privacy": "public", "users_can_create_markers": "yes" }'
``` 

<details>
<summary>Response:</summary>
<p> 

```json
{
  "title": "wzLCVsqaKigXNVA",
  "description": "mdCLuzFqxRRKDefWAEtaJoeXLoAdVlEeFXsOfueTSSSBrerDMqBM",
  "slug": "c98d62ce-c5cc-4d1e-bbc3-1d470051dcd0",
  "uuid": "c98d62ce-c5cc-4d1e-bbc3-1d470051dcd0",
  "token": "v0pBMK0YryEmOSGxye2RaEDTN9LzDyoq",
  "privacy": "public",
  "users_can_create_markers": "yes",
  "updated_at": "2021-05-24T22:41:23+02:00",
  "created_at": "2021-05-24T22:41:23+02:00"
}  
```

</p>
</details> 

2. Create a marker:

* Request:

```bash
curl -v -X POST 'https://cartes.io/api/maps/c98d62ce-c5cc-4d1e-bbc3-1d470051dcd0/markers/' -H 'Accept: application/json, application/javascript, text/javascript, text/json' -H 'Content-Type: application/json' -d '{ "category_name": "XcpsaMCqxGQovSYEgu", "lng": 38.146976540215874, "map_token": "v0pBMK0YryEmOSGxye2RaEDTN9LzDyoq", "description": "evrYkCvKMslUKnqDgypIlnE", "category": -1, "lat": 33.31327227913496 }'
``` 

<details>
<summary>Response:</summary>
<p> 

```json
  {
  "category_id": 198,
  "token": "Jur27Zs4AVNQo9V4Ytpc7zkviElanFDV",
  "description": "evrYkCvKMslUKnqDgypIlnE",
  "location": {
    "type": "Point",
    "coordinates": [
      33.31327227913496,
      38.146976540215874
    ]
  },
  "expires_at": null,
  "updated_at": "2021-05-24T22:41:24+02:00",
  "created_at": "2021-05-24T22:41:24+02:00",
  "id": 1231,
  "map": {
    "slug": "c98d62ce-c5cc-4d1e-bbc3-1d470051dcd0",
    "title": "wzLCVsqaKigXNVA",
    "description": "mdCLuzFqxRRKDefWAEtaJoeXLoAdVlEeFXsOfueTSSSBrerDMqBM",
    "privacy": "public",
    "users_can_create_markers": "yes",
    "options": null,
    "uuid": "c98d62ce-c5cc-4d1e-bbc3-1d470051dcd0",
    "created_at": "2021-05-24T22:41:23+02:00",
    "updated_at": "2021-05-24T22:41:24+02:00"
  },
  "category": {
    "id": 198,
    "name": "XcpsaMCqxGQovSYEgu",
    "slug": "xcpsamcqxgqovsyegu",
    "icon": "/images/marker-01.svg"
  }
}

```

</p>
</details> 

3. Send PUT request to update the marker:

* Request:

```bash
curl -v -X PUT 'https://cartes.io/api/maps/c98d62ce-c5cc-4d1e-bbc3-1d470051dcd0/markers/1231' -H 'Accept: application/json, application/javascript, text/javascript, text/json' -H 'Content-Type: application/json' -d '{ "category_name": "iDpLsuqfxaqQPkTEoSymppHp", "lng": 143.5237561360062, "map_token": "v0pBMK0YryEmOSGxye2RaEDTN9LzDyoq", "description": "fctYWjFYXPtjSNZlAMMjUFiRoywj", "category": -1, "lat": 45.188089836442515 }'
``` 

* Response:  Status code 403

```json
 {
  "message": "This action is unauthorized."
}
``` 

## Expected:

### Status code 200 OK, body contains updated marker values