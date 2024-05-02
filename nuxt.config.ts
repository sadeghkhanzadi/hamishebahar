export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: ['@pinia/nuxt','@nuxtjs/tailwindcss','nuxt-aos'],
  css:["~/assets/style/main.scss"],
  app:{
    head:{
      htmlAttrs:{
        dir:'rtl'
      }
    }
  },
  aos:{
    once: true,
    duration:1500,
    offset: 50,
  }
})