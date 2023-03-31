package tnj.Framework;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

public class SoundManager {
    // 멤버변수
    private SoundPool m_SoundPool;    // 안드로이드에서 지원하는 사운드풀
    private HashMap m_SoundPoolMap; // 불러온 사운드의 아이디값을 지정할 해시맵
    private AudioManager m_AudioManager; // 사운드 관리를 위한 오디오 매니저
    private Context m_Activity;     // 애플리케이션의 컨텍스트 값
    private MediaPlayer m_Sound_Background1;
    private MediaPlayer m_Sound_Background2;

    public void Init(Context _context) {
        // 멤버변수 생성과 초기화
        m_SoundPool = new SoundPool.Builder().build(); // 롤리팝 이후 SoundPool생성 문법
        m_SoundPoolMap = new HashMap();
        m_AudioManager = (AudioManager)_context.getSystemService(Context.AUDIO_SERVICE);
        m_Activity = _context;

        m_Sound_Background1 = MediaPlayer.create(_context, R.raw.gamestate);
        m_Sound_Background2 = MediaPlayer.create(_context, R.raw.bossstate);

        SoundManager.getInstance().addSound(0, R.raw.enemy);
        SoundManager.getInstance().addSound(1, R.raw.missile);

    }
    //배경음악
    public void startSound(int _index){
        if(_index == 0 ){
            m_Sound_Background1.start();
        }else{
            m_Sound_Background2.start();
        }
    }
    public void pauseSound(int _index){
        if(_index == 0 ){
            m_Sound_Background1.pause();

        }else{
            m_Sound_Background2.pause();
        }

    }
    //효과음
    public void addSound( int _index, int _soundID) {
        int id = m_SoundPool.load(m_Activity, _soundID, 1); // 사운드를 로드
        m_SoundPoolMap.put(_index, id); // 해시맵에 아이디 값을 받아 온 인덱스 저장
    }
    public void play( int _index) {
        // 사운드재생
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume /= m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer)m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, 0, 1f);
    }
    public void playLooped( int _index) {
        // 사운드 반복재생
        float streamVolume = m_AudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        streamVolume /= m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        m_SoundPool.play((Integer)m_SoundPoolMap.get(_index), streamVolume, streamVolume, 1, -1, 1f);
    }
    public void stop(int _index){

        m_SoundPool.stop((Integer)m_SoundPoolMap.get(_index));
    }

    private static SoundManager s_instance;
    public static SoundManager getInstance( ) {
        if(s_instance== null) s_instance= new SoundManager( );
        return s_instance;
    }
}

/*
// 사운드 매니저 사용을 위한 초기화
SoundManager.getInstance().Init(액티비티값);
// 사운드 리소스를 불러오는 과정
SoundManager.getInstance().addSound(1, R.raw.sound1);
SoundManager.getInstance().addSound(1, R.raw.sound2);
SoundManager.getInstance().addSound(1, R.raw.sound3);
// 사운드 재생
SoundManager.getInstance().play(1);
SoundManager.getInstance().playLooped(2);
SoundManager.getInstance().play(3);
*/

/*
// 인덱스 상수화
public static final int SOUND_EFFECT_1 = 1;
public static final int SOUND_EFFECT_2 = 2;
publics tatic final int SOUND_EFFECT_3 = 3;
// 사운드 리소스 불러오기
SoundManager.getInstance().addSound(SOUND_EFFECT_1, R.raw.sound1);
SoundManager.getInstance().addSound(SOUND_EFFECT_2, R.raw.sound1);
SoundManager.getInstance().addSound(SOUND_EFFECT_3, R.raw.sound1);
// 사운드 재생
SoundManager.getInstance().play(SOUND_EFFECT_1);
SoundManager.getInstance().playLooped(SOUND_EFFECT_2);
SoundManager.getInstance().play(SOUND_EFFECT_3);
 */