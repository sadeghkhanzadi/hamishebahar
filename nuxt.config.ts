export default defineNuxtConfig({
  devtools: { enabled: true },
  modules: ['@pinia/nuxt','@nuxtjs/tailwindcss'],
  css:["~/assets/style/main.scss"],
  app:{
    head:{
      htmlAttrs:{
        dir:'rtl'
      }
    }
  }
})