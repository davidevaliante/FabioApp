package kot.slimmer.fabioapp

data class User(var isSuperUser : Boolean=false,
                var name : String,
                var surname : String,
                var phone : String,
                var lastLogin : Long ,
                var registration : Long)

data class Cut(var name : String? = null,
               var date : Long? = null,
               var year : Int? = null,
               var weekOfYear : Int? = null,
               var dayOfYear : Int? = null,
               var hour : Int? = null,
               var minute : Int? = null,
               var bookingDate : Long? = null,
               var confirmed : Boolean? = null )

data class TimeInterval(var start : Cut? = null,
                        var end : Cut? = null)

