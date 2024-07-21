export default defineNuxtConfig({

  devtools: { enabled: true },
  runtimeConfig: {
    public: {
      apiBaseUrl:'https://hamishebahar-service.liara.run'
    }
  },

  modules: ['@pinia/nuxt','@nuxtjs/tailwindcss','nuxt-aos'],
  css:["~/assets/style/main.scss"],
  app:{
    head:{
      link:[{rel:'icon',type:'image/png' , href:'/logo/logo.png'}],
      htmlAttrs:{
        dir:'rtl',
        lang:"fa"
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
  },
})
