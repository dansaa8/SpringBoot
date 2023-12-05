# Om användare är inloggad, så ska hen kunna hämta ut även
# enstaka privata med locations/id  <- (id där isPrivate = true)

* Om användare är authenticatad, så ska getOnemetoden i service
* kalla på en specifik metod i repositoryt som kollar letar
* efter det med skickade id:et som både kollar efter 
* isPrivate = false bland allihopa, och även efter alla där
* den authenticatede användaren är satt som userid, oavsett..
* boolean isPrivate's värde.