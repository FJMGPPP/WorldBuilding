package com.fjmg.worldbuilding.data.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.fjmg.worldbuilding.R;
import com.fjmg.worldbuilding.utils.Utils;

import java.io.Serializable;
import java.util.Objects;
@Entity
public class Universo implements Serializable , Comparable<Universo>
{
    @Ignore
    public static final String TAG = "universo";
    @Ignore
    public static final String ID = "id";
    String uidUsuario;
    @Ignore
    static final public int BITMAP = 1;
    @Ignore
    static final public int DRAWDEABLE = 2;
    @PrimaryKey(autoGenerate = true)
    long id;
    private String titulo;
    private String descripcion;
    @Ignore
    transient private Drawable imagenDrawdeable;
    @Ignore
    transient private Bitmap imagenBitmap;
    @Ignore
    public boolean tieneImagen;
    @Ignore
    private int tipo;

    String base64;
    @Ignore
    public Universo(String uidUsuario,String titulo, String descripcion) {
        this.uidUsuario = uidUsuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        tieneImagen = true;
        tipo= BITMAP;
        this.base64 = Utils.IMAGEN_DEFAULT;
        imagenBitmap = Utils.BitmapDecode(base64);
    }
    @Ignore
    public Universo(String uidUsuario,long id,String titulo, String descripcion,   Drawable imagenDrawdeable) {
        this.id = id;
        this.uidUsuario = uidUsuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenDrawdeable = imagenDrawdeable;
        tieneImagen = true;
        tipo= DRAWDEABLE;
        this.base64 = Utils.DrawdeableEncode(imagenDrawdeable);

    }

    public Universo(String uidUsuario,long id, String titulo, String descripcion, String base64) {
        this.uidUsuario = uidUsuario;
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.base64 = base64;
        if (base64 != null)
        {
           imagenBitmap = Utils.BitmapDecode(base64);
        }else
            {
                this.base64 = Utils.DrawdeableEncode(imagenDrawdeable);
                imagenBitmap = Utils.BitmapDecode(base64);

            }
        tieneImagen = true;
        tipo = BITMAP;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Drawable getImagenDrawdeable() {
        return imagenDrawdeable;
    }
    public void setImagenDrawdeable(Drawable imagenDrawdeable) {
        this.imagenDrawdeable = imagenDrawdeable;
        setTipo(DRAWDEABLE);
    }

    public Bitmap getImagenBitmap() {
        return imagenBitmap;
    }
    public void setImagenBitmap(Bitmap imagenBitmap) {
        this.imagenBitmap = imagenBitmap;
        this.base64 = Utils.BitmapEncode(imagenBitmap);
        setTipo(BITMAP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Universo universo = (Universo) o;
        return Objects.equals(titulo, universo.titulo) && Objects.equals(descripcion, universo.descripcion) && Objects.equals(imagenDrawdeable, universo.imagenDrawdeable);
    }
    public long getId() {
        return id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(titulo, descripcion, imagenDrawdeable);
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public void setImageView(ImageView view) throws Exception {
        switch (tipo)
        {
            case BITMAP:
                view.setImageBitmap(this.imagenBitmap);
                break;
            case DRAWDEABLE:
                view.setImageDrawable(this.imagenDrawdeable);
                break;
        }
    }

    @Override
    public int compareTo(Universo universo) {
        if(universo.titulo.compareTo(this.titulo) == 0)
        {
            if (universo.id > id)
            {
                return  1;
            } else
            {
                return  -1;
            }
        }
        return universo.titulo.compareTo(this.titulo);
    }

    public String getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(String uidUsuario) {
        this.uidUsuario = uidUsuario;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
