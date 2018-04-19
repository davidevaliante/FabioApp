package kot.slimmer.fabioapp

data class User(val isSuperUser : Boolean=false,
                val name : String,
                val surname : String,
                val phone : String,
                val lastLogin : Long ,
                val registration : Long)

data class Cut(val name : String,
               val date : Long,
               val year : Int,
               val weekOfYear : Int,
               val dayOfYear : Int,
               val hour : Int,
               val minute : Int,
               val bookingDate : Long,
               val confirmed : Boolean )
