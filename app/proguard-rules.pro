-keepattributes Signature

-keepclassmembers class com.origin.auxilio_emergencial.models.** {
      *;
}


#-keep @kotlinx.android.parcel.Parcelize public class *

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}