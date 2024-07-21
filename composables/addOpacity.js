export default function addAlpha(color, opacity) {
     let c
    // coerce values so it is between 0 and 1.
     if (color.length>=6){
         c = color.substring(0,4) + Math.round((opacity*255)).toString(16)
     }
     else {
          c = color + Math.round((opacity * 255)).toString(16)
     }
}
