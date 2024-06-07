export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: ['@pinia/nuxt','@nuxtjs/tailwindcss','nuxt-aos'],
  css:["~/assets/style/main.scss"],
  app:{
    head:{
      link:[{rel:'icon',type:'image/png' , href:'/logo/logo.png'}],
      htmlAttrs:{
        dir:'rtl'
      }
    }
  },
  features:{
    inlineStyles:false
  },
  aos:{
    once: true,
    duration:1500,
    offset: 50,
  }
})