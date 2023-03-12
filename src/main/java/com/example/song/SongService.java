/*

 * You can use the following import statements
  
 * import org.springframework.http.HttpStatus;
 * import org.springframework.web.server.ResponseStatusException;

 */

package com.example.song;

import java.util.*;

import com.example.song.Song;
import com.example.song.SongRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

// Don't modify the below code
public class SongService implements SongRepository {
    private static HashMap<Integer, Song> playlist = new HashMap<>();

    public SongService() {
        playlist.put(1, new Song(1, "Butta Bomma", "Ramajogayya Sastry", "Armaan Malik", "Thaman S"));
        playlist.put(2, new Song(2, "Kathari Poovazhagi", "Vijay", "Benny Dayal, Swetha Mohan", "A.R. Rahman"));
        playlist.put(3, new Song(3, "Tum Hi Ho", "Mithoon", "Arijit Singh", "Mithoon"));
        playlist.put(4, new Song(4, "Vizhiyil", "Vairamuthu", "Unni Menon", "A.R. Rahman"));
        playlist.put(5, new Song(5, "Nenjame", "Panchu Arunachalam", "S.P.Balasubrahmanyam", "Ilaiyaraaja"));
    }

    // Don't modify the above code

    // Write your code here

    int uniqueId=6;

    @Override
    public ArrayList<Song> getSongs(){
        Collection<Song> totalList=playlist.values();
        ArrayList<Song> songs=new ArrayList<>(totalList);
        return songs;
    }

    @Override
    public Song getSongById(int songId){
        Song song=playlist.get(songId);
        if(song==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return song;
    }

    @Override

    public Song addSong(Song song){
        song.setSongId(uniqueId);
        playlist.put(uniqueId,song);
        uniqueId+=1;
        return song;
    }

    @Override
    public Song updateSong(int songId, Song song){
        Song existingSong=playlist.get(songId);

        if(existingSong == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(song.getSongName() != null){
            existingSong.setSongName(song.getSongName());
        }
        if(song.getLyricist() != null){
            existingSong.setLyricist(song.getLyricist());
        }
        if(song.getSinger() != null){
            existingSong.setSinger(song.getSinger());
        }
        if(song.getMusicDirector() != null){
            existingSong.setMusicDirector(song.getMusicDirector());
        }

        return existingSong;
    }

    @Override
    public void deleteSong(int songId){
        Song song=playlist.get(songId);
        if(song==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else{
            playlist.remove(songId);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}