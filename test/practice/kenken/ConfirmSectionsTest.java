/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package practice.kenken;
import java.util.List;
import static practice.kenken.KenKen.createSections;
import static practice.kenken.KenKen.confirmSections;


/**
 * @author derek
 */
public class ConfirmSectionsTest {
    public static void main(String[] args) {
        List<Section> sections = createSections("5, 1, 4, 3+, 2, 3#, 3, 6, 5+, 5, 8, 9, 4+, 7, 3#");
        int[] knownGoodArr = new int[]{1,3,2,2,1,3,3,2,1};
        System.out.println(confirmSections(knownGoodArr, sections));
    }

}
