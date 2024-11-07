"use client"

import { Template, ImageCard } from '@/components'
import {useImageService} from '@/resources/image/image-service'
import {Image} from '@/resources/image/image-resource'
import { useState } from 'react'


export default function GaleriaPage(){
 
    const userService = useImageService();
    const [images, setImages] = useState<Image[]>([])

    async function searchImage(){
           const result = await userService.buscar();
           setImages(result);
           console.table(images);
    }

    return(
        
        <Template>
        <button className='bg-gray-500' onClick={searchImage}>Clique para mudar</button>
            <section className="grid grid-cols-3 gap-8">

            <ImageCard nome="Lago" tamanho="10MB" dataUpload="01/01/2024"/>
            <ImageCard nome="Lago" tamanho="10MB" dataUpload="01/01/2024"/>
           
            </section>
            
        </Template>
    )
}